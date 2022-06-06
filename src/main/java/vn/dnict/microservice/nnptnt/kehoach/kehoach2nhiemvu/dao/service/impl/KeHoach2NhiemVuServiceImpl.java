package vn.dnict.microservice.nnptnt.kehoach.kehoach2nhiemvu.dao.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.kehoach2nhiemvu.dao.model.KeHoach2NhiemVu;
import vn.dnict.microservice.nnptnt.kehoach.kehoach2nhiemvu.dao.service.KeHoach2NhiemVuService;
import vn.dnict.microservice.nnptnt.kehoach.kehoach2nhiemvu.dao.service.KeHoach2NhiemVuSpecifications;
import vn.dnict.microservice.nnptnt.kehoach.kehoach2nhiemvu.dao.service.repo.KeHoach2NhiemVuRepo;

@Service
@Transactional
public class KeHoach2NhiemVuServiceImpl implements KeHoach2NhiemVuService {
	@Autowired
	private KeHoach2NhiemVuRepo repo;

	@Override
	public KeHoach2NhiemVu save(KeHoach2NhiemVu keHoach2NhiemVu) {
		return repo.save(keHoach2NhiemVu);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<KeHoach2NhiemVu> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public int setFixedDaXoaForKeHoachKhacId(boolean daXoa, Long keHoachKhacId) {
		return repo.setFixedDaXoaForKeHoachKhacId(daXoa, keHoachKhacId);
	}

	@Override
	public List<KeHoach2NhiemVu> findByKeHoachKhacIdAndDaXoa(Long keHoachKhacId, boolean daXoa) {
		return repo.findByKeHoachKhacIdAndDaXoa(keHoachKhacId, daXoa);
	}

	@Override
	public List<KeHoach2NhiemVu> findByKeHoachKhacIdAndDaXoaAndNhiemVuChaIdIsNull(Long keHoachKhacId, boolean daXoa) {
		return repo.findByKeHoachKhacIdAndDaXoaAndNhiemVuChaIdIsNull(keHoachKhacId, daXoa);
	}

	@Override
	public List<KeHoach2NhiemVu> findByKeHoachKhacIdAndNhiemVuChaIdAndDaXoaOrderBySapXepAsc(Long keHoachKhacId, Long nhiemVuChaId,
			boolean daXoa) {
		return repo.findByKeHoachKhacIdAndNhiemVuChaIdAndDaXoaOrderBySapXepAsc(keHoachKhacId, nhiemVuChaId, daXoa);
	}

	@Override
	public List<KeHoach2NhiemVu> findByKeHoachKhacIdAndNhiemVuChaIdAndDonViThucHienIdAndDaXoaOrderBySapXepAsc(Long keHoachKhacId,
			Long nhiemVuChaId, Long donViThucHienId, boolean daXoa) {
		return repo.findByKeHoachKhacIdAndNhiemVuChaIdAndDonViThucHienIdAndDaXoaOrderBySapXepAsc(keHoachKhacId, nhiemVuChaId, donViThucHienId, daXoa);
	}

	@Override
	public List<KeHoach2NhiemVu> findByKeHoachKhacIdAndNhiemVuChaIdAndPhongBanThucHienIdAndDaXoaOrderBySapXepAsc(Long keHoachKhacId,
			Long nhiemVuChaId, Long phongBanThucHienId, boolean daXoa) {
		return repo.findByKeHoachKhacIdAndNhiemVuChaIdAndPhongBanThucHienIdAndDaXoaOrderBySapXepAsc(keHoachKhacId, nhiemVuChaId, phongBanThucHienId,
				daXoa);
	}

	@Override
	public List<KeHoach2NhiemVu> findByKeHoachKhacIdAndDaXoaAndDonViThucHienIdAndNhiemVuChaIdIsNull(Long keHoachKhacId, Long donViThucHienId,
			boolean daXoa) {
		return repo.findByKeHoachKhacIdAndDaXoaAndDonViThucHienIdAndNhiemVuChaIdIsNull(keHoachKhacId, donViThucHienId, daXoa);
	}

	@Override
	public List<KeHoach2NhiemVu> findByKeHoachKhacIdAndDaXoaAndPhongBanThucHienIdAndNhiemVuChaIdIsNull(Long keHoachKhacId,
			Long phongBanThucHienId, boolean daXoa) {
		return repo.findByKeHoachKhacIdAndDaXoaAndPhongBanThucHienIdAndNhiemVuChaIdIsNull(keHoachKhacId, phongBanThucHienId, daXoa);
	}

	@Override
	public List<KeHoach2NhiemVu> findAll(Long keHoachKhacId, Long nhiemVuChaId, Boolean isBanHanh, Boolean isThucHien,
			List<Long> nguonKinhPhiIds, List<Integer> tinhTrangs, LocalDate thoiGianThucHienTuNgay, LocalDate thoiGianThucHienDenNgay,
			LocalDate thoiGianThanhToanTuNgay, LocalDate thoiGianThanhToanDenNgay, List<Long> donViPhoiHopIds, List<Long> phongBanPhoiHopIds,
			Long donViThucHienId, Long phongBanThucHienId) {
		return repo.findAll(KeHoach2NhiemVuSpecifications.quichSearch(keHoachKhacId, nhiemVuChaId, isBanHanh, isThucHien, nguonKinhPhiIds,
				tinhTrangs, thoiGianThucHienTuNgay, thoiGianThucHienDenNgay, thoiGianThanhToanTuNgay, thoiGianThanhToanDenNgay, donViPhoiHopIds,
				phongBanPhoiHopIds, donViThucHienId, phongBanThucHienId));
	}

}
