package vn.dnict.microservice.danhmuc.business;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import vn.dnict.microservice.core.data.bean.CanBo;
import vn.dnict.microservice.danhmuc.dao.model.DmCanBo;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.model.DmPhongBan;
import vn.dnict.microservice.danhmuc.dao.service.DmCanBoService;
import vn.dnict.microservice.danhmuc.data.DmCanBoInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@Service
public class DmCanBoBusiness {
	@Autowired
	private DmCanBoService canBoService;

	public Page<DmCanBo> findAll(int page, int size, String sortBy, String sortDir, String search, String hoTen,
			Long phongBanId, Long donViId, Long chucVuId, String email, String diaChi, String cmndSo, Long gioiTinhId,
			LocalDate tuNgaySinh, LocalDate denNgaySinh, Long donViChaId) {
		Sort sortable = null;
		if ("ASC".equals(sortDir)) {
			sortable = Sort.by(sortBy).ascending();
		}
		if ("DESC".equals(sortDir)) {
			sortable = Sort.by(sortBy).descending();
		}
		Page<DmCanBo> pageCanBo = canBoService.findAll(search, hoTen, phongBanId, donViId, chucVuId, email, diaChi,
				cmndSo, gioiTinhId, tuNgaySinh, denNgaySinh, donViChaId, PageRequest.of(page, size, sortable));
		return pageCanBo;
	}

	public DmCanBo findById(Long id) throws EntityNotFoundException {
		Optional<DmCanBo> optional = canBoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmCanBo.class, "id=", String.valueOf(id));
		}
		return optional.get();
	}

	public DmCanBo findByEmail(String email) {
		List<DmCanBo> listCanBo = canBoService.findByEmailIgnoreCaseAndDaXoa(email, 0);
		System.out.println("listCanBo: " + listCanBo);
		DmCanBo canBo = new DmCanBo();
		if (listCanBo != null && !listCanBo.isEmpty()) {
			canBo = listCanBo.get(0);
		}
		return canBo;
	}
	
	public List<DmCanBo> findByPhongBan(Long phongBanId, Integer daXoa) {
		List<DmCanBo> listCanBo = canBoService.findDsCanBoByPhongBanIdAndDaXoa(phongBanId, 0);
		System.out.println("listCanBo: " + listCanBo);
		return listCanBo;
	}
	
	public DmDonVi findDonViByEmail(String email) throws EntityNotFoundException {
		List<DmCanBo> listCanBo = canBoService.findByEmailIgnoreCaseAndDaXoa(email, 0);
		DmCanBo canBo = new DmCanBo();
		if (listCanBo != null && !listCanBo.isEmpty()) {
			canBo = listCanBo.get(0);
		}
		if (canBo.getDonVi() == null) {
			throw new EntityNotFoundException(DmDonVi.class, "Khong tim thay don vi cua", String.valueOf(canBo));
		}
		return canBo.getDonVi();
	}

	public DmPhongBan findPhongBanByEmail(String email) throws EntityNotFoundException {
		List<DmCanBo> listCanBo = canBoService.findByEmailIgnoreCaseAndDaXoa(email, 0);
		DmCanBo canBo = new DmCanBo();
		if (listCanBo != null && !listCanBo.isEmpty()) {
			canBo = listCanBo.get(0);
		}
		if (canBo.getPhongBan() == null) {
			throw new EntityNotFoundException(DmPhongBan.class, "Khong tim thay phong ban cua", String.valueOf(canBo));
		}
		return canBo.getPhongBan();
	}

	public DmDonVi findDonViByCanBoId(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmCanBo> optional = canBoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmCanBo.class, "id=", String.valueOf(id));
		}
		DmCanBo canBo = optional.get();
		if (canBo.getDonVi() == null) {
			throw new EntityNotFoundException(DmDonVi.class, "Khong tim thay don vi cua", String.valueOf(canBo));
		}
		return canBo.getDonVi();
	}

	public DmPhongBan findPhongBanByCanBoId(Long id) throws EntityNotFoundException {
		Optional<DmCanBo> optional = canBoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmCanBo.class, "id=", String.valueOf(id));
		}
		DmCanBo canBo = optional.get();
		if (canBo.getPhongBan() == null) {
			throw new EntityNotFoundException(DmPhongBan.class, "Khong tim thay phong ban cua :",
					String.valueOf(canBo));
		}
		return canBo.getPhongBan();
	}

	public List<DmCanBo> findByIdIn(@PathVariable("ids") List<Long> ids) {
		return canBoService.findByIdIn(ids);
	}

	public DmCanBo create(DmCanBoInput canBoInput) throws EntityNotFoundException {
		DmCanBo canBo = new DmCanBo();
		canBo.setDaXoa(0);
		canBo.setChucVu(canBoInput.getChucVu());
		canBo.setHoTen(canBoInput.getHoTen());
		canBo.setPhongBan(canBoInput.getPhongBan());
		canBo.setDonVi(canBoInput.getDonVi());
		canBo.setEmail(canBoInput.getEmail());
		canBo.setNgaySinh(canBoInput.getNgaySinh());
		canBo.setDiaChi(canBoInput.getDiaChi());
		canBo.setDienThoai(canBoInput.getDienThoai());
		canBo.setFax(canBoInput.getFax());
		canBo.setCmndSo(canBoInput.getCmndSo());
		canBo.setCmndNgayCap(canBoInput.getCmndNgayCap());
		canBo.setCmndNoiCap(canBoInput.getCmndNoiCap());
		canBo.setGioiTinh(canBoInput.getGioiTinh());
		// canBo.setAppCode(appCode);
		canBo = canBoService.save(canBo);

		return canBo;
	}

	public DmCanBo update(Long id, @Valid @RequestBody DmCanBoInput canBoInput) throws EntityNotFoundException {
		Optional<DmCanBo> optional = canBoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmCanBo.class, "id=", String.valueOf(id));
		}
		DmCanBo canBo = optional.get();
		canBo.setChucVu(canBoInput.getChucVu());
		canBo.setHoTen(canBoInput.getHoTen());
		canBo.setPhongBan(canBoInput.getPhongBan());
		canBo.setDonVi(canBoInput.getDonVi());
		canBo.setEmail(canBoInput.getEmail());
		canBo.setNgaySinh(canBoInput.getNgaySinh());
		canBo.setDiaChi(canBoInput.getDiaChi());
		canBo.setDienThoai(canBoInput.getDienThoai());
		canBo.setFax(canBoInput.getFax());
		canBo.setCmndSo(canBoInput.getCmndSo());
		canBo.setCmndNgayCap(canBoInput.getCmndNgayCap());
		canBo.setCmndNoiCap(canBoInput.getCmndNoiCap());
		canBo.setGioiTinh(canBoInput.getGioiTinh());
		// canBo.setAppCode(appCode);
		canBo = canBoService.save(canBo);
		return canBo;
	}

	public DmCanBo delete(Long id) throws EntityNotFoundException {
		Optional<DmCanBo> optional = canBoService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CanBo.class, "id=", String.valueOf(id));
		}
		DmCanBo canBo = optional.get();
		canBo.setDaXoa(1);
		canBo = canBoService.save(canBo);
		return canBo;
	}

}
