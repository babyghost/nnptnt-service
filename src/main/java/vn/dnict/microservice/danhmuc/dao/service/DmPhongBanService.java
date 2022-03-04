package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmPhongBan;

public interface DmPhongBanService {
	public DmPhongBan save(DmPhongBan phongBan);

	public Optional<DmPhongBan> findById(Long id);

	public void delete(Long id);

	public Page<DmPhongBan> findAll(String search, Long donViId, Boolean trangThai, Pageable pageable);

	public Page<DmPhongBan> findByDaXoaAndSearch(Integer daXoa, String search, Pageable pageable);

	public List<DmPhongBan> findByIdIn(List<Long> idList);

	public List<DmPhongBan> findByDonViIdAndDaXoa(Long donViId, Integer daXoa);
}
