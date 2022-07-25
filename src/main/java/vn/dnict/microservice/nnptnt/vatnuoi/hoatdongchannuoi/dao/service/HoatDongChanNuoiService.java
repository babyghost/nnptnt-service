package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.vatnuoi.data.ThongKeSoLuongChanNuoiData;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;

@Service
public interface HoatDongChanNuoiService {
	public HoatDongChanNuoi save(HoatDongChanNuoi entity);

	public void deleteById(Long id);

	public Optional<HoatDongChanNuoi> findById(Long id);

	public Page<HoatDongChanNuoi> findAll(String tenCoSo, String tenChuCoSo, String dienThoai, Long quanHuyenId,
			Long phuongXaId, String nam, Integer quy, Pageable pageable);

	public List<Object[]> thongKeSoVatNuoi(String nam, List<Long> loaiVatNuoiIds, List<Integer> quy);

	public List<HoatDongChanNuoi> findQuyByNamAndcoSoChanNuoiId(String nam, Long coSoChanNuoiId);

	public List<HoatDongChanNuoi> findByCoSoChanNuoiIdAndDaXoa(Long coSoChanNuoiId, Boolean daXoa);

	public List<HoatDongChanNuoi> findByCoSoChanNuoiIdAndNamAndQuyAndDaXoa(Long coSoChanNuoiId, String nam, Integer quy,
			Boolean daXoa);

	public Long tongSoLuongVatNuoi(String nam, List<Integer> quy, List<Long> loaiVatNuoiIds);

	public Page<HoatDongChanNuoi> thongKeSoLuong(String nam, List<Integer> quy, List<Long> loaiVatNuoiIds,
			Pageable pageable);

	// ------------------------------ Thong Ke Hoat Dong
	// ----------------------------------------

	public Page<HoatDongChanNuoi> thongKeHoatDong(String tenCoSo, String tenChuCoSo, List<Long> loaiVatNuoiIds,
			Long quanHuyenId, Long phuongXaId, String nam, List<Integer> quys, Pageable pageable);

	public Page<Object> thongKeSoVatNuoiDemo(String nam, List<Long> loaiVatNuoiIds, List<Integer> quy,
			Pageable pageable);

}
