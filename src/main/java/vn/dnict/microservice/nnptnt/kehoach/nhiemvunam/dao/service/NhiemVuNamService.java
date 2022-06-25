package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;

public interface NhiemVuNamService {
	public NhiemVuNam save(NhiemVuNam nhiemVuNam);

	public void deleteById(Long id);

	public Optional<NhiemVuNam> findById(Long id);

	public List<NhiemVuNam> findByKeHoachIdAndDaXoa(Long keHoachId, Boolean daXoa);

	public int setFixedDaXoaForKeHoachId(Boolean daXoa, Long keHoachId);

	public List<NhiemVuNam> findByKeHoachIdAndNhiemVuChaIdIsNullAndDaXoa(Long keHoachId, Boolean daXoa);

	public List<NhiemVuNam> findByKeHoachIdAndNhiemVuChaIdAndDaXoa(Long keHoachId, Long nhiemVuChaId, Boolean daXoa);

	public List<NhiemVuNam> findByKeHoachIdAndLoaiNhiemVuIdAndNhiemVuChaIdIsNullAndDaXoa(Long keHoachId, Long loaiNhiemVuId,
			Boolean daXoa);

	public List<NhiemVuNam> findByKeHoachIdAndLoaiNhiemVuIdAndNhiemVuChaIdAndDaXoa(Long keHoachId, Long loaiNhiemVuId,
			Long nhiemVuChaId, Boolean daXoa);

	public Page<NhiemVuNam> findAll(Long donViChuTriId, Integer tinhTrang, Integer nam, Long keHoachId, LocalDate tuNgay,
			LocalDate denNgay, String tenNhiemVu, Pageable pageable);

	public List<NhiemVuNam> getThongKeSoLuong(Long donViChuTriId, Integer nam, Long keHoachId, List<Integer> tinhTrangs,
			LocalDate tuNgay, LocalDate denNgay, String tenNhiemVu);
}
