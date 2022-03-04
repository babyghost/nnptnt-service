package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.danhmuc.dao.model.DmPhongBan;

@Service
public class DmPhongBanServiceImpl implements DmPhongBanService{
	@Autowired
	private DmPhongBanRepo phongBanRepo;
	public DmPhongBan save (DmPhongBan phongBan) {
		return phongBanRepo.save(phongBan);
	}

	public Optional<DmPhongBan> findById(Long id) {
		return phongBanRepo.findById(id);
	}
	public void delete(Long id) {
		phongBanRepo.deleteById(id);
	}
	public Page<DmPhongBan> findAll(String search, Long donViId, Boolean trangThai, Pageable pageable){
		return phongBanRepo.findAll(DmPhongBanSpecifications.quickSearch(search, donViId, trangThai), pageable);
	}
	public Page<DmPhongBan> findByDaXoaAndSearch(Integer daXoa, String search, Pageable pageable){
		return phongBanRepo.findAll(DmPhongBanSpecifications.findByDaXoaAndSearch(daXoa, search), pageable);
	}

	public List<DmPhongBan> findByIdIn(List<Long> idList){
		return phongBanRepo.findByIdIn(idList);
	}
	public List<DmPhongBan> findByDonViIdAndDaXoa(Long donViId, Integer daXoa) {
		return phongBanRepo.findByDonViIdAndDaXoa(donViId, daXoa);
	}
}
