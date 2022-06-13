package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;

public interface NhiemVuNamService {
	public NhiemVuNam save(NhiemVuNam entity);
	
	public void deleteById(Long id);
	
	public Optional<NhiemVuNam> findById(Long id);
	
	public List<NhiemVuNam> findByKeHoachNamIdAndDaXoa(Long keHoachNamId, Boolean daXoa);
	
	public List<NhiemVuNam> getByKeHoachNamIdAndNhiemVuChaIdIsNullAndDaXoa(Long keHoachNamId, Boolean daXoa);
	
	public List<NhiemVuNam> getByKeHoachNamIdAndNhiemVuChaIdAndDaXoa(Long keHoachNamId, Long nhiemVuCha, Boolean daXoa);
	
	public int setFixedDaXoaForKeHoachNamId(Boolean daXoa, Long keHoachNamId);
	
	public Optional<NhiemVuNam> findByKeHoachNamId(Long keHoachNamId);
	
	public Page<NhiemVuNam> findAll(Long donViChuTriId, Long keHoachNamId, Integer nam, Boolean tinhTrang, String tenNhiemVu, LocalDate tuNgay, LocalDate denNgay, Pageable pageable);
	
	public Page<NhiemVuNam> tongHopKeHoachNam(Long donViChuTriId, Long keHoachNamId, Integer nam, Boolean tinhTrang, String tenNhiemVu, LocalDate tuNgay,
			LocalDate denNgay, Pageable pageable);
}
