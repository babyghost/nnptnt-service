package vn.dnict.microservice.core.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.core.dao.model.CoreNhomChucNang;

@Service
@Transactional
public class CoreNhomChucNangServiceImpl implements CoreNhomChucNangService {

	@Autowired
	private CoreNhomChucNangRepo repo;

	public Optional<CoreNhomChucNang> findById(Long id) {
		return repo.findById(id);
	}

	public CoreNhomChucNang save(CoreNhomChucNang coreNhomChucNang) {
		return repo.save(coreNhomChucNang);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Page<CoreNhomChucNang> findAll(String search, Boolean trangThai, Long id, Long nhomChucNangChaId,
			Pageable pageable) {
		return repo.findAll(CoreNhomChucNangSpecifications.quickSearch(search, trangThai, id, nhomChucNangChaId),
				pageable);
	}

	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	public List<CoreNhomChucNang> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa) {
		return repo.findByMaIgnoreCaseAndDaXoa(ma, daXoa);
	}

	public List<CoreNhomChucNang> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa) {
		return repo.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, daXoa);
	}

	public List<CoreNhomChucNang> findByTrangThaiAndDaXoa(Boolean trangThai, Boolean daXoa) {
		return repo.findByTrangThaiAndDaXoa(trangThai, daXoa);
	}

	public List<CoreNhomChucNang> findByNhomChucNangChaIdAndTrangThaiAndDaXoaOrderBySapXepAsc(Long nhomChucNangChaId,
			Boolean trangThai, Boolean daXoa) {
		return repo.findByNhomChucNangChaIdAndTrangThaiAndDaXoaOrderBySapXepAsc(nhomChucNangChaId, trangThai, daXoa);
	}

	public List<CoreNhomChucNang> findByNhomChucNangChaIdIsNullAndTrangThaiAndDaXoaOrderBySapXepAsc(Boolean trangThai,
			Boolean daXoa) {
		return repo.findByNhomChucNangChaIdIsNullAndTrangThaiAndDaXoaOrderBySapXepAsc(trangThai, daXoa);
	}

	public int setFixedDaXoa(Boolean daXoa) {
		return repo.setFixedDaXoa(daXoa);
	}

	public List<CoreNhomChucNang> findByMaIgnoreCase(String ma) {
		return repo.findByMaIgnoreCase(ma);
	}

}
