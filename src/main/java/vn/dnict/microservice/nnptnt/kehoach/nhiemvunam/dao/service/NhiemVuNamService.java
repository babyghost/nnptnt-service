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

	public List<NhiemVuNam> findByKeHoachNamIdAndDaXoa(Long keHoachNamId, Boolean daXoa);

	public int setFixedDaXoaForKeHoachNamId(Boolean daXoa, Long keHoachId);

	public List<NhiemVuNam> findByKeHoachNamIdAndNhiemVuChaIdIsNullAndDaXoa(Long keHoachNamId, Boolean daXoa);

	public List<NhiemVuNam> findByKeHoachNamIdAndNhiemVuChaIdAndDaXoa(Long keHoachNamId, Long nhiemVuChaId, Boolean daXoa);

	public List<NhiemVuNam> findByKeHoachNamIdAndLoaiNhiemVuIdAndNhiemVuChaIdIsNullAndDaXoa(Long keHoachNamId, Long loaiNhiemVuId,
			Boolean daXoa);

	public List<NhiemVuNam> findByKeHoachNamIdAndLoaiNhiemVuIdAndNhiemVuChaIdAndDaXoa(Long keHoachNamId, Long loaiNhiemVuId,
			Long nhiemVuChaId, Boolean daXoa);
	
	public Page<NhiemVuNam> findAll(Long donViChuTriId, List<Integer> tinhTrangs, Integer nam, Long keHoachNamId, LocalDate tuNgay,
			LocalDate denNgay, String tenNhiemVu, Pageable pageable);

	public List<NhiemVuNam> getThongKeSoLuong(Long donViChuTriId, Integer nam, Long keHoachNamId, List<Integer> tinhTrangs,
			LocalDate tuNgay, LocalDate denNgay, String tenNhiemVu);
}
