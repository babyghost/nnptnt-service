package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;

public interface DmDonViService {
	public DmDonVi save(DmDonVi DmDonVi);

	public Optional<DmDonVi> findById(Long id);

	public void delete(Long id);

	public Page<DmDonVi> findAll(String search, String tenDmDonVi, String diaChi, String soDienThoai, String email, Long DmDonViChaId, Long capId, Long loaiDmDonViId, Pageable pageable);

	public Page<DmDonVi> findByDaXoaAndSearch(Boolean daXoa, String search, Pageable pageable);

	public Page<DmDonVi> findByDaXoaAndTrangThaiAndSearch(Boolean daXoa, Integer trangThai, String search,
			Pageable pageable);

	public List<DmDonVi> findByIdIn(List<Long> idList);

	public List<DmDonVi> listCha();

	public List<DmDonVi> listCha(List<DmDonVi> listCha, List<DmDonVi> listKetQua, String hienThi);

	public List<DmDonVi> findByDmDonViChaIdAndDaXoa(Long DmDonViChaId, Boolean daXoa);

	public List<DmDonVi> findByDaXoa(Boolean daXoa);
}
