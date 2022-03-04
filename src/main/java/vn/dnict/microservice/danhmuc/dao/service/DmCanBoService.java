package vn.dnict.microservice.danhmuc.dao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmCanBo;

public interface DmCanBoService {
	public Optional<DmCanBo> findById(Long id);
	
	public Optional<DmCanBo> findFirstByEmailAndDaXoa(String email, Integer daXoa);

	public List<DmCanBo> findByEmailAndDaXoa(String email, Integer daXoa);
	
	public List<DmCanBo> findDsCanBoByPhongBanIdAndDaXoa(Long phongBan, Integer daXoa);
	
	public List<DmCanBo> findDsCanBoByDonViIdAndDaXoa(Long donVi, Integer daXoa);

	public DmCanBo save(DmCanBo canBo);

	public void delete(Long id);

	public Page<DmCanBo> findAll(String search, String hoTen, Long phongBanId, Long donViId, Long chucVuId,
			String email, String diaChi, String cmndSo, Long gioiTinhId, LocalDate tuNgaySinh, LocalDate denNgaySinh,
			Long donViChaId, Pageable pageable);

	public Page<DmCanBo> findByDaXoaAndSearch(String search, Pageable pageable);

	public List<DmCanBo> findByIdIn(List<Long> idList);

	public List<DmCanBo> findByEmailIgnoreCaseAndDaXoa(String email, Integer daXoa);
}
