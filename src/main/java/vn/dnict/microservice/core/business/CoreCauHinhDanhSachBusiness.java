package vn.dnict.microservice.core.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import vn.dnict.microservice.core.dao.model.CoreCauHinhDanhSach;
import vn.dnict.microservice.core.dao.model.DanhSachHienThi;
import vn.dnict.microservice.core.dao.service.CoreCauHinhDanhSachService;
import vn.dnict.microservice.core.dao.service.DanhSachHienThiService;
import vn.dnict.microservice.core.data.CoreCauHinhDanhSachInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@Service
public class CoreCauHinhDanhSachBusiness {

	@Autowired
	private CoreCauHinhDanhSachService cauHinhDanhSachService;
	@Autowired
	private DanhSachHienThiService danhSachHienThiService;

	public Page<CoreCauHinhDanhSach> findAll(int page, int size, String sortBy, String sortDir,
			String maDanhSach, String tenCot, Boolean trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<CoreCauHinhDanhSach> pageList = cauHinhDanhSachService.findAll(maDanhSach, tenCot, trangThai,
				PageRequest.of(page, size, direction, sortBy));
		return pageList;
	}

	public CoreCauHinhDanhSach findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<CoreCauHinhDanhSach> optional = cauHinhDanhSachService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreCauHinhDanhSach.class, "id", String.valueOf(id));
		}
		return optional.get();
	}

	public CoreCauHinhDanhSach create(String appCode, CoreCauHinhDanhSachInput modelInput) {
		CoreCauHinhDanhSach cauHinhDanhSach = new CoreCauHinhDanhSach();
		cauHinhDanhSach.setMaDanhSach(modelInput.getMadanhsach());
		cauHinhDanhSach.setTenCot(modelInput.getTenCot());
		cauHinhDanhSach.setDoRongCot(modelInput.getDoRongCot());
		cauHinhDanhSach.setSapXep(modelInput.getSapXep());
		cauHinhDanhSach.setTrangThai(modelInput.getTrangThai());
		cauHinhDanhSach.setAppCode(appCode);
		cauHinhDanhSach.setDaXoa(false);
		cauHinhDanhSach = cauHinhDanhSachService.save(cauHinhDanhSach);
		return cauHinhDanhSach;
	}

	public CoreCauHinhDanhSach update(String appCode, Long id, CoreCauHinhDanhSachInput modelInput)
			throws EntityNotFoundException {
		Optional<CoreCauHinhDanhSach> optional = cauHinhDanhSachService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreCauHinhDanhSach.class, "id", String.valueOf(id));
		}
		CoreCauHinhDanhSach cauHinhDanhSach = optional.get();
		cauHinhDanhSach.setMaDanhSach(modelInput.getMadanhsach());
		cauHinhDanhSach.setTenCot(modelInput.getTenCot());
		cauHinhDanhSach.setDoRongCot(modelInput.getDoRongCot());
		cauHinhDanhSach.setSapXep(modelInput.getSapXep());
		cauHinhDanhSach.setTrangThai(modelInput.getTrangThai());
		cauHinhDanhSach.setAppCode(appCode);
		cauHinhDanhSach.setDaXoa(false);
		cauHinhDanhSach = cauHinhDanhSachService.save(cauHinhDanhSach);
		return cauHinhDanhSach;
	}

	public CoreCauHinhDanhSach delete(String appCode, Long id) throws EntityNotFoundException {
		Optional<CoreCauHinhDanhSach> optional = cauHinhDanhSachService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoreCauHinhDanhSach.class, "id", String.valueOf(id));
		}
		CoreCauHinhDanhSach cauHinhDanhSach = optional.get();
		cauHinhDanhSach.setDaXoa(true);
		cauHinhDanhSach.setAppCode(appCode);
		cauHinhDanhSach = cauHinhDanhSachService.save(cauHinhDanhSach);
		return cauHinhDanhSach;
	}

	public List<DanhSachHienThi> findByMaDanhSachAndNguoiSuDung(String maDanhSach, String nguoiSuDung) {
		return danhSachHienThiService.findByMaDanhSachAndNguoiSuDung(maDanhSach, nguoiSuDung);
	}

}
