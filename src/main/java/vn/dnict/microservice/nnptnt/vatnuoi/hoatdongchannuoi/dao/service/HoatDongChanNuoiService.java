package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;

@Service
public interface HoatDongChanNuoiService {
	public HoatDongChanNuoi save(HoatDongChanNuoi entity);

	public void deleteById(Long id);

	public Optional<HoatDongChanNuoi> findById(Long id);

	public Page<HoatDongChanNuoi> findAll(String tenCoSo, String tenChuCoSo, String dienThoai, Long quanHuyenId,
			Long phuongXaId, String nam, Integer quy, Pageable pageable);
	
	public List<HoatDongChanNuoi> findByCoSoChanNuoiIdAndDaXoa(Long coSoChanNuoiId, Boolean daXoa);
	
	public List<HoatDongChanNuoi> findByCoSoChanNuoiIdAndNamAndQuyAndDaXoa(Long coSoChanNuoiId, String nam, Integer quy, Boolean daXoa);
}
