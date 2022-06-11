package vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.model.DmLoaiNhiemVu;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service.DmLoaiNhiemVuService;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service.DmLoaiNhiemVuSpecifications;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service.repo.DmLoaiNhiemVuRepo;

@Service
public class DmLoaiNhiemVuServiceImpl implements DmLoaiNhiemVuService {
	@Autowired
	private DmLoaiNhiemVuRepo repo;

	@Override
	public DmLoaiNhiemVu save(DmLoaiNhiemVu DmLoaiNhiemVu) {
		return repo.save(DmLoaiNhiemVu);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<DmLoaiNhiemVu> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public Page<DmLoaiNhiemVu> findAll(String search, Boolean trangThai, Pageable pageable) {
		return repo.findAll(DmLoaiNhiemVuSpecifications.quichSearch(search, trangThai), pageable);
	}

	@Override
	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public List<DmLoaiNhiemVu> findByMaIgnoreCaseAndDaXoa(String ma, boolean daXoa) {
		return repo.findByMaIgnoreCaseAndDaXoa(ma, daXoa);
	}

	@Override
	public List<DmLoaiNhiemVu> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, boolean daXoa) {
		return repo.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, daXoa);
	}

}
