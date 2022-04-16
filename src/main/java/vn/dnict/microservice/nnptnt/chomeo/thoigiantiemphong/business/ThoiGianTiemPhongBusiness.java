package vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.business;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.danhmuc.dao.service.DmPhuongXaService;
import vn.dnict.microservice.danhmuc.dao.service.DmQuanHuyenService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.data.ChuQuanLyData;
import vn.dnict.microservice.nnptnt.chomeo.data.ThoiGianTiemPhongData;
import vn.dnict.microservice.nnptnt.chomeo.data.ThoiGianTiemPhongData;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.service.KeHoachTiemPhongService;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.model.ThoiGianTiemPhong;
import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.service.ThoiGianTiemPhongService;

@Service
public class ThoiGianTiemPhongBusiness {
	@Autowired
	ThoiGianTiemPhongService serviceThoiGianTiemPhongService;
	@Autowired 
	KeHoachTiemPhongService serviceKeHoachTiemPhongService;
	@Autowired
	DmQuanHuyenService serviceDmQuanHuyenService;

	@Autowired
	DmPhuongXaService serviceDmPhuongXaService;
	
	public Page<ThoiGianTiemPhongData> findAll(int page, int size, String sortBy, String sortDir,Long phuongXaId,Long quanHuyenId,LocalDate thoiGianTiemDenNgay, LocalDate thoiGianTiemTuNgay, Long keHoachTiemPhongId, String diaDiem) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		} 
		final Page<ThoiGianTiemPhong> pageThoiGianTiemPhong = serviceThoiGianTiemPhongService.findAll(phuongXaId, quanHuyenId, thoiGianTiemDenNgay, thoiGianTiemTuNgay, keHoachTiemPhongId, diaDiem, 
				PageRequest.of(page, size, direction, sortBy));
		final Page<ThoiGianTiemPhongData> pageThoiGianTiemPhongData = pageThoiGianTiemPhong.map(this::convertToThoiGianTiemPhongData);
		return pageThoiGianTiemPhongData;
	}
	public ThoiGianTiemPhongData findById(Long id) throws EntityNotFoundException {
		Optional<ThoiGianTiemPhong> optional = serviceThoiGianTiemPhongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThoiGianTiemPhong.class, "id", String.valueOf(id));
		}
		ThoiGianTiemPhong thoiGianTiemPhong = optional.get();
		return this.convertToThoiGianTiemPhongData(thoiGianTiemPhong);
	}
	
	private ThoiGianTiemPhongData convertToThoiGianTiemPhongData(ThoiGianTiemPhong thoiGianTiemPhong) {
		ThoiGianTiemPhongData thoiGianTiemPhongData = new ThoiGianTiemPhongData();
		thoiGianTiemPhongData.setId(thoiGianTiemPhong.getId());
		thoiGianTiemPhongData.setDiaDiem(thoiGianTiemPhong.getDiaDiem());
		thoiGianTiemPhongData.setKeHoachTiemPhongId(thoiGianTiemPhong.getKeHoachTiemPhongId());
		thoiGianTiemPhongData.setPhuongXaId(thoiGianTiemPhong.getPhuongXaId());
		thoiGianTiemPhongData.setQuanHuyenId(thoiGianTiemPhong.getQuanHuyenId());
		thoiGianTiemPhongData.setThoiGianTiemTuNgay(thoiGianTiemPhong.getThoiGianTiemTuNgay());
		thoiGianTiemPhongData.setThoiGianTiemDenNgay(thoiGianTiemPhong.getThoiGianTiemDenNgay());
		if (thoiGianTiemPhong.getQuanHuyenId() != null && thoiGianTiemPhong.getQuanHuyenId() > 0) {
			Optional<DmQuanHuyen> optional = serviceDmQuanHuyenService.findById(thoiGianTiemPhong.getQuanHuyenId());
			if (optional.isPresent()) {
				thoiGianTiemPhongData.setQuanHuyenTen(optional.get().getTen());
			}
		}
		if (thoiGianTiemPhong.getPhuongXaId() != null && thoiGianTiemPhong.getPhuongXaId() > 0) {
			Optional<DmPhuongXa> optional = serviceDmPhuongXaService.findById(thoiGianTiemPhong.getPhuongXaId());
			if (optional.isPresent()) {
				thoiGianTiemPhongData.setQuanHuyenTen(optional.get().getTen());
			}
		}
		
		
		return thoiGianTiemPhongData ;
	}
	
	
	public ThoiGianTiemPhongData create(ThoiGianTiemPhongData ThoiGianTiemPhongData) {
		ThoiGianTiemPhong ThoiGianTiemPhong = new ThoiGianTiemPhong();
		ThoiGianTiemPhong.setDaXoa(false);
		ThoiGianTiemPhong.setPhuongXaId(ThoiGianTiemPhongData.getPhuongXaId());
		ThoiGianTiemPhong.setQuanHuyenId(ThoiGianTiemPhongData.getQuanHuyenId());
		ThoiGianTiemPhong.setKeHoachTiemPhongId(ThoiGianTiemPhongData.getKeHoachTiemPhongId());
		ThoiGianTiemPhong.setThoiGianTiemTuNgay(ThoiGianTiemPhongData.getThoiGianTiemTuNgay());
		ThoiGianTiemPhong.setThoiGianTiemDenNgay(ThoiGianTiemPhongData.getThoiGianTiemDenNgay());
		ThoiGianTiemPhong.setDiaDiem(ThoiGianTiemPhongData.getDiaDiem());
		ThoiGianTiemPhong = serviceThoiGianTiemPhongService.save(ThoiGianTiemPhong);
		
		
		return this.convertToThoiGianTiemPhongData(ThoiGianTiemPhong);
	}
	
	

	public ThoiGianTiemPhongData update(Long id, ThoiGianTiemPhongData ThoiGianTiemPhongData) throws EntityNotFoundException {
		Optional<ThoiGianTiemPhong> optional = serviceThoiGianTiemPhongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThoiGianTiemPhong.class, "id", String.valueOf(id));
		}
		ThoiGianTiemPhong ThoiGianTiemPhong = optional.get();
		ThoiGianTiemPhong.setPhuongXaId(ThoiGianTiemPhongData.getPhuongXaId());
		ThoiGianTiemPhong.setQuanHuyenId(ThoiGianTiemPhongData.getQuanHuyenId());
		ThoiGianTiemPhong.setKeHoachTiemPhongId(ThoiGianTiemPhongData.getKeHoachTiemPhongId());
		ThoiGianTiemPhong.setThoiGianTiemTuNgay(ThoiGianTiemPhongData.getThoiGianTiemTuNgay());
		ThoiGianTiemPhong.setThoiGianTiemDenNgay(ThoiGianTiemPhongData.getThoiGianTiemDenNgay());
		ThoiGianTiemPhong.setDiaDiem(ThoiGianTiemPhongData.getDiaDiem());
		ThoiGianTiemPhong = serviceThoiGianTiemPhongService.save(ThoiGianTiemPhong);
		return this.convertToThoiGianTiemPhongData(ThoiGianTiemPhong);
	}

	@DeleteMapping(value = { "/{id}" })
	public ThoiGianTiemPhongData delete(Long id) throws EntityNotFoundException {
		Optional<ThoiGianTiemPhong> optional = serviceThoiGianTiemPhongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThoiGianTiemPhong.class, "id", String.valueOf(id));
		}
		ThoiGianTiemPhong ThoiGianTiemPhong = optional.get();
		ThoiGianTiemPhong.setDaXoa(true);
		ThoiGianTiemPhong = serviceThoiGianTiemPhongService.save(ThoiGianTiemPhong);
		return this.convertToThoiGianTiemPhongData(ThoiGianTiemPhong);
	}
}

