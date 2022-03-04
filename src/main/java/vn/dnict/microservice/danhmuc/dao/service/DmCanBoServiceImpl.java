package vn.dnict.microservice.danhmuc.dao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.danhmuc.dao.model.DmCanBo;

@Service
@Transactional
public class DmCanBoServiceImpl implements DmCanBoService {
	@Autowired
	private DmCanBoRepo repo;

	public Optional<DmCanBo> findById(Long id) {
		return repo.findById(id);
	}
	
	public Optional<DmCanBo> findFirstByEmailAndDaXoa(String email, Integer daXoa) {
		return repo.findFirstByEmailAndDaXoa(email, daXoa);
	}

	public List<DmCanBo> findByEmailAndDaXoa(String email, Integer daXoa) {
		return repo.findByEmailAndDaXoa(email, daXoa);
	}
	
	public List<DmCanBo> findDsCanBoByPhongBanIdAndDaXoa(Long phongBan, Integer daXoa) {
		return repo.findDsCanBoByPhongBanIdAndDaXoa(phongBan, daXoa);
	}
	
	public List<DmCanBo> findDsCanBoByDonViIdAndDaXoa(Long donVi, Integer daXoa) {
		return repo.findDsCanBoByDonViIdAndDaXoa(donVi, daXoa);
	}

	public DmCanBo save(DmCanBo canBo) {
		return repo.save(canBo);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Page<DmCanBo> findAll(String search, Pageable pageable) {
		return repo.findAll(pageable);
	}

	public Page<DmCanBo> findByDaXoaAndSearch(String search, Pageable pageable) {
		return repo.findAll(DmCanBoSpecifications.findByDaXoaAndSearch(search), pageable);
	}

	public List<DmCanBo> findByIdIn(List<Long> idList) {
		return repo.findByIdIn(idList);
	}

	public Page<DmCanBo> findAll(String search, String hoTen, Long phongBanId, Long donViId, Long chucVuId,
			String email, String diaChi, String cmndSo, Long gioiTinhId, LocalDate tuNgaySinh, LocalDate denNgaySinh,
			Long donViChaId, Pageable pageable) {
		return repo.findAll(DmCanBoSpecifications.quickSearch(search, hoTen, phongBanId, donViId, chucVuId, email,
				diaChi, cmndSo, gioiTinhId, tuNgaySinh, denNgaySinh, donViChaId), pageable);
	}

	public List<DmCanBo> findByEmailIgnoreCaseAndDaXoa(String email, Integer daXoa) {
		return repo.findByEmailIgnoreCaseAndDaXoa(email, daXoa);
	}

}
