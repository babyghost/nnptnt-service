package vn.dnict.microservice.nnptnt.ocop.danhgia.bussiness;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.phanhang.dao.model.DmPhanHang;
import vn.dnict.microservice.nnptnt.dm.phanhang.dao.service.DmPhanHangService;
import vn.dnict.microservice.nnptnt.ocop.danhgia.dao.model.DanhGiaSanPham;
import vn.dnict.microservice.nnptnt.ocop.danhgia.dao.service.DanhGiaSanPhamService;
import vn.dnict.microservice.nnptnt.ocop.data.DanhGiaSanPhamData;
@Service
public class DanhGiaSanPhamBussiness {
	@Autowired
	DanhGiaSanPhamService serviceDanhGiaSanPhamService;
	@Autowired
	DmPhanHangService servicePhanHangService;
	
	public Page<DanhGiaSanPhamData> findAll(int page, int size, String sortBy, String sortDir,
			Long sanPhamId, Long phanHangId ) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		Page<DanhGiaSanPham> pageDanhGiaSanPham= serviceDanhGiaSanPhamService.findAll(sanPhamId, phanHangId, 
				PageRequest.of(page, size, direction, sortBy));
		final Page<DanhGiaSanPhamData> pageDanhGiaSanPhamData = pageDanhGiaSanPham.map(this::convertToDanhGiaSanPhamData);
		return pageDanhGiaSanPhamData;
	}
	
	
	private DanhGiaSanPhamData convertToDanhGiaSanPhamData(DanhGiaSanPham danhGiaSanPham) {
		DanhGiaSanPhamData danhGiaSanPhamData = new DanhGiaSanPhamData();
		danhGiaSanPhamData.setId(danhGiaSanPham.getId());
		danhGiaSanPhamData.setIsChamDiem(danhGiaSanPham.getIsChamDiem());
		danhGiaSanPhamData.setSanPhamId(danhGiaSanPham.getSanPhamId());
		danhGiaSanPhamData.setTieuChiId(danhGiaSanPham.getTieuChiId());
		danhGiaSanPhamData.setTongDiemBaPhan(danhGiaSanPham.getTongDiemBaPhan());
		danhGiaSanPhamData.setNgayTao(danhGiaSanPham.getNgayTao());
		if (Objects.nonNull(danhGiaSanPham.getPhanHangId())) {
			Optional<DmPhanHang> optPhanHang = servicePhanHangService.findById(danhGiaSanPham.getPhanHangId());
			if(optPhanHang.isPresent()) {
				danhGiaSanPhamData.setPhanHangId(optPhanHang.get().getId());
				danhGiaSanPhamData.setPhanHangTen(optPhanHang.get().getTen());
			}
		}
		return danhGiaSanPhamData;
	}
	
	public DanhGiaSanPhamData findById(Long id) throws EntityNotFoundException {
		Optional<DanhGiaSanPham> optional = serviceDanhGiaSanPhamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DanhGiaSanPham.class, "id", String.valueOf(id));
		}
		return this.convertToDanhGiaSanPhamData(optional.get());
	}
	
	public DanhGiaSanPham create(DanhGiaSanPhamData danhGiaSanPhamData) {
		DanhGiaSanPham danhGiaSanPham = new DanhGiaSanPham();
		danhGiaSanPham.setDaXoa(false);
		danhGiaSanPham.setIsChamDiem(danhGiaSanPhamData.getIsChamDiem());
		danhGiaSanPham.setSanPhamId(danhGiaSanPhamData.getSanPhamId());
		danhGiaSanPham.setTieuChiId(danhGiaSanPhamData.getTieuChiId());
		danhGiaSanPham.setTongDiemBaPhan(danhGiaSanPhamData.getTongDiemBaPhan());
		danhGiaSanPham.setPhanHangId(danhGiaSanPhamData.getPhanHangId());
		danhGiaSanPham = serviceDanhGiaSanPhamService.save(danhGiaSanPham);
		return danhGiaSanPham;
	}
	
	public DanhGiaSanPham update(Long id, DanhGiaSanPhamData danhGiaSanPhamData) throws EntityNotFoundException {
		Optional<DanhGiaSanPham> optional = serviceDanhGiaSanPhamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DanhGiaSanPham.class, "id", String.valueOf(id));
		}
		
		DanhGiaSanPham danhGiaSanPham = optional.get();
		danhGiaSanPham.setDaXoa(false);
		danhGiaSanPham.setIsChamDiem(danhGiaSanPhamData.getIsChamDiem());
		danhGiaSanPham.setSanPhamId(danhGiaSanPhamData.getSanPhamId());
		danhGiaSanPham.setTieuChiId(danhGiaSanPhamData.getTieuChiId());
		danhGiaSanPham.setTongDiemBaPhan(danhGiaSanPhamData.getTongDiemBaPhan());
		danhGiaSanPham.setPhanHangId(danhGiaSanPhamData.getPhanHangId());
		danhGiaSanPham = serviceDanhGiaSanPhamService.save(danhGiaSanPham);
		return danhGiaSanPham;
	}
	
	public DanhGiaSanPham delete(Long id) throws EntityNotFoundException {
		Optional<DanhGiaSanPham> optional = serviceDanhGiaSanPhamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DanhGiaSanPham.class, "id", String.valueOf(id));
		}
		DanhGiaSanPham danhGiaSanPham = optional.get();
		danhGiaSanPham.setDaXoa(true);
		danhGiaSanPham = serviceDanhGiaSanPhamService.save(danhGiaSanPham);
		return danhGiaSanPham;
	}
		
}
