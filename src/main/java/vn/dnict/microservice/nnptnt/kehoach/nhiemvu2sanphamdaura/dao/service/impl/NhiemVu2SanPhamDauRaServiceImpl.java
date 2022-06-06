package vn.dnict.microservice.nnptnt.kehoach.nhiemvu2sanphamdaura.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2sanphamdaura.dao.model.NhiemVu2SanPhamDauRa;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2sanphamdaura.dao.service.NhiemVu2SanPhamDauRaService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvu2sanphamdaura.dao.service.repo.NhiemVu2SanPhamDauRaRepo;


@Service
@Transactional
public class NhiemVu2SanPhamDauRaServiceImpl implements NhiemVu2SanPhamDauRaService {
	@Autowired
	private NhiemVu2SanPhamDauRaRepo repo;

	@Override
	public NhiemVu2SanPhamDauRa save(NhiemVu2SanPhamDauRa nhiemVu2SanPhamDauRa) {
		return repo.save(nhiemVu2SanPhamDauRa);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<NhiemVu2SanPhamDauRa> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public int setFixedDaXoaForKeHoach2NhiemVuId(boolean daXoa, Long keHoach2NhiemVuId) {
		return repo.setFixedDaXoaForKeHoach2NhiemVuId(daXoa, keHoach2NhiemVuId);
	}

	@Override
	public List<NhiemVu2SanPhamDauRa> findByKeHoach2NhiemVuIdAndDaXoa(Long keHoach2NhiemVuId, boolean daXoa) {
		return repo.findByKeHoach2NhiemVuIdAndDaXoa(keHoach2NhiemVuId, daXoa);
	}

	@Override
	public Optional<NhiemVu2SanPhamDauRa> findFirstByKeHoach2NhiemVuIdAndSanPhamDinhKemId(Long keHoach2NhiemVuId, Long sanPhamDauRaId) {
		return repo.findFirstByKeHoach2NhiemVuIdAndSanPhamDinhKemId(keHoach2NhiemVuId, sanPhamDauRaId);
	}

}
