package vn.dnict.microservice.core.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.core.dao.model.CoreChucNang;

@Service
@Transactional
public class CoreChucNangServiceImpl implements CoreChucNangService {
	@Autowired
	private CoreChucNangRepo repo;

	public Optional<CoreChucNang> findById(Long id) {
		return repo.findById(id);
	}

	public CoreChucNang save(CoreChucNang coreChucNang) {
		return repo.save(coreChucNang);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Page<CoreChucNang> findAll(String search, Long nhomChucNangId, Boolean trangThai, Pageable pageable) {
		return repo.findAll(CoreChucNangSpecifications.quickSearch(search, nhomChucNangId, trangThai), pageable);
	}

	public Boolean existsById(Long id) {
		return repo.existsById(id);
	}
	
	public List<CoreChucNang> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa) {
		return repo.findByMaIgnoreCaseAndDaXoa(ma, daXoa);
	}

	public List<CoreChucNang> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa) {
		return repo.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, daXoa);
	}

	public List<CoreChucNang> findByCoreNhomChucNangIdAndTrangThaiAndDaXoa(Long nhomChucNangId, Boolean trangThai,
			Boolean daXoa) {
		return repo.findByCoreNhomChucNangIdAndTrangThaiAndDaXoaOrderBySapXepAsc(nhomChucNangId, trangThai, daXoa);
	}

	public int setFixedDaXoaForNhomChucNangId(Boolean daXoa, Long nhomChucNangId) {
		return repo.setFixedDaXoaForNhomChucNangId(daXoa, nhomChucNangId);
	}

	@Override
	public int setFixedDaXoa(Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.setFixedDaXoa(daXoa);
	}

	@Override
	public List<CoreChucNang> findByMaIgnoreCase(String ma) {
		// TODO Auto-generated method stub
		return repo.findByMaIgnoreCase(ma);
	}

	@Override
	public List<String> getChucNangMas(String roleName) {
		// TODO Auto-generated method stub
		return repo.getChucNangMas(roleName);
	}
	
}
