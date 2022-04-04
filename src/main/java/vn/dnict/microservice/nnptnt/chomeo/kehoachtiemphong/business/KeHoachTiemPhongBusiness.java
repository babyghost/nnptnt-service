package vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.data.ChuQuanLyData;
import vn.dnict.microservice.nnptnt.chomeo.data.KeHoachTiemPhongData;
import vn.dnict.microservice.nnptnt.chomeo.data.KeHoachTiemPhongInput;
import vn.dnict.microservice.nnptnt.chomeo.data.ThongTinChoMeoData;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.service.KeHoach2ChoMeoService;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.model.KeHoachTiemPhong;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.service.KeHoachTiemPhongService;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.service.ThoiGianTiemPhongService;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model.ThongTinChoMeo;
import vn.dnict.microservice.nnptnt.dm.giong.dao.model.DmGiong;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.model.DmLoaiDongVat;

@Service
public class KeHoachTiemPhongBusiness {
	@Autowired
	KeHoachTiemPhongService serviceKeHoachTiemPhongService;
	@Autowired
	ThoiGianTiemPhongService serviceThoiGianTiemPhongService;
	@Autowired
	KeHoach2ChoMeoService serviceKeHoach2ChoMeoService;
	
	public Page<KeHoachTiemPhongData> findAll(int page, int size, String sortBy, String sortDir,String noiDung, String soKeHoach, String tenKeHoach, LocalDate ngayBanHanhTuNgay, LocalDate ngayBanHanhDenNgay,LocalDate ngayDuKienTuNgay,LocalDate ngayDuKienDenNgay) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
	    final	Page<KeHoachTiemPhong> pageKeHoachTiemPhong = serviceKeHoachTiemPhongService.findAll( noiDung, soKeHoach, tenKeHoach, ngayBanHanhTuNgay, ngayBanHanhDenNgay, ngayDuKienTuNgay, ngayDuKienDenNgay,
				PageRequest.of(page, size, direction, sortBy));
	    final   Page<KeHoachTiemPhongData> pageKeHoachTiemPhongData = pageKeHoachTiemPhong.map(this::convertToKeHoachTiemPhongData);
		return pageKeHoachTiemPhongData;
	}
	public KeHoachTiemPhongData findById(Long id) throws EntityNotFoundException {
		Optional<KeHoachTiemPhong> optional = serviceKeHoachTiemPhongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachTiemPhong.class, "id", String.valueOf(id));
		}
		KeHoachTiemPhong keHoachTiemPhong = optional.get();
		KeHoachTiemPhongData keHoachTiemPhongData = new KeHoachTiemPhongData();
		keHoachTiemPhongData.setId(keHoachTiemPhong.getId());
		keHoachTiemPhongData.setTenKeHoach(keHoachTiemPhong.getTenKeHoach());
		keHoachTiemPhongData.setSoKeHoach(keHoachTiemPhong.getSoKeHoach());
		keHoachTiemPhongData.setNoiDung(keHoachTiemPhong.getNoiDung());
		keHoachTiemPhongData.setNgayBanHanh(keHoachTiemPhong.getNgayBanHanh());
		keHoachTiemPhongData.setNgayDuKienTuNgay(keHoachTiemPhong.getNgayDuKienTuNgay());
		keHoachTiemPhongData.setNgayDuKienDenNgay(keHoachTiemPhong.getNgayDuKienDenNgay());
		
		
		return keHoachTiemPhongData;
	}


	private KeHoachTiemPhongData convertToKeHoachTiemPhongData(KeHoachTiemPhong keHoachTiemPhong) {
		KeHoachTiemPhongData keHoachTiemPhongData = new KeHoachTiemPhongData();
		keHoachTiemPhongData.setId(keHoachTiemPhong.getId());
		keHoachTiemPhongData.setTenKeHoach(keHoachTiemPhong.getTenKeHoach());
		keHoachTiemPhongData.setSoKeHoach(keHoachTiemPhong.getSoKeHoach());
		keHoachTiemPhongData.setNoiDung(keHoachTiemPhong.getNoiDung());
		keHoachTiemPhongData.setNgayBanHanh(keHoachTiemPhong.getNgayBanHanh());
		keHoachTiemPhongData.setNgayDuKienTuNgay(keHoachTiemPhong.getNgayDuKienTuNgay());
		keHoachTiemPhongData.setNgayDuKienDenNgay(keHoachTiemPhong.getNgayDuKienDenNgay());
		
		return keHoachTiemPhongData;
	}
	
	public KeHoachTiemPhong create(KeHoachTiemPhongData KeHoachTiemPhongData) {
		KeHoachTiemPhong KeHoachTiemPhong = new KeHoachTiemPhong();
		KeHoachTiemPhong.setDaXoa(false);
		KeHoachTiemPhong.setTenKeHoach(KeHoachTiemPhongData.getTenKeHoach());
		KeHoachTiemPhong.setSoKeHoach(KeHoachTiemPhongData.getSoKeHoach());
		KeHoachTiemPhong.setNoiDung(KeHoachTiemPhongData.getNoiDung());
		KeHoachTiemPhong.setNgayBanHanh(KeHoachTiemPhongData.getNgayBanHanh());
		KeHoachTiemPhong.setNgayDuKienTuNgay(KeHoachTiemPhongData.getNgayDuKienTuNgay());
		KeHoachTiemPhong.setNgayDuKienDenNgay(KeHoachTiemPhongData.getNgayDuKienDenNgay());
		KeHoachTiemPhong = serviceKeHoachTiemPhongService.save(KeHoachTiemPhong);
		return KeHoachTiemPhong;
	}

	public KeHoachTiemPhong update(Long id, KeHoachTiemPhongData KeHoachTiemPhongData) throws EntityNotFoundException {
		Optional<KeHoachTiemPhong> optional = serviceKeHoachTiemPhongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachTiemPhong.class, "id", String.valueOf(id));
		}
		KeHoachTiemPhong KeHoachTiemPhong = optional.get();
		KeHoachTiemPhong.setTenKeHoach(KeHoachTiemPhongData.getTenKeHoach());
		KeHoachTiemPhong.setSoKeHoach(KeHoachTiemPhongData.getSoKeHoach());
		KeHoachTiemPhong.setNoiDung(KeHoachTiemPhongData.getNoiDung());
		KeHoachTiemPhong.setNgayBanHanh(KeHoachTiemPhongData.getNgayBanHanh());
		KeHoachTiemPhong.setNgayDuKienTuNgay(KeHoachTiemPhongData.getNgayDuKienTuNgay());
		KeHoachTiemPhong.setNgayDuKienDenNgay(KeHoachTiemPhongData.getNgayDuKienDenNgay());
		KeHoachTiemPhong = serviceKeHoachTiemPhongService.save(KeHoachTiemPhong);
		return KeHoachTiemPhong;
	}

	@DeleteMapping(value = { "/{id}" })
	public KeHoachTiemPhongData delete(Long id) throws EntityNotFoundException {
		Optional<KeHoachTiemPhong> optional = serviceKeHoachTiemPhongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachTiemPhong.class, "id", String.valueOf(id));
		}
		KeHoachTiemPhong KeHoachTiemPhong = optional.get();
		KeHoachTiemPhong.setDaXoa(true);
		KeHoachTiemPhong = serviceKeHoachTiemPhongService.save(KeHoachTiemPhong);
		return this.convertToKeHoachTiemPhongData(KeHoachTiemPhong);
	}
}