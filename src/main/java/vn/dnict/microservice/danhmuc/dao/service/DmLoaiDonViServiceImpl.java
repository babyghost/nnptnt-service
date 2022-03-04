package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.danhmuc.dao.model.DmLoaiDonVi;

@Service
public class DmLoaiDonViServiceImpl implements DmLoaiDonViService{
	@Autowired
	private DmLoaiDonViRepo loaiDonViRepo;
	
	public DmLoaiDonVi save (DmLoaiDonVi loaiDonVi) {
		return loaiDonViRepo.save(loaiDonVi);
	}

	public Optional<DmLoaiDonVi> findById(Long id) {
		return loaiDonViRepo.findById(id);
	}
	public void delete(Long id) {
		loaiDonViRepo.deleteById(id);
	}
	public Page<DmLoaiDonVi> findAll(String search, Boolean trangThai, Pageable pageable){
		return loaiDonViRepo.findAll(DmLoaiDonViSpecifications.quickSearch(search, trangThai), pageable);
	}
	public Page<DmLoaiDonVi> findByDaXoaAndSearch(Integer daXoa, String search, Pageable pageable){
		return loaiDonViRepo.findAll(DmLoaiDonViSpecifications.findByDaXoaAndSearch(daXoa, search), pageable);
	}
	public List<DmLoaiDonVi> findByIdIn(List<Long> idList){
		return loaiDonViRepo.findByIdIn(idList);
	}
	
}
