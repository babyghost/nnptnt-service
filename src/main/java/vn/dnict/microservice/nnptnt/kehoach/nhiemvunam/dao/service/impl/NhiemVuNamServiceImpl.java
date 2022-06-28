package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.service.NhiemVuNamService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.service.NhiemVuNamSpecifications;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.service.repo.NhiemVuNamRepo;

@Service
@Transactional
public class NhiemVuNamServiceImpl implements NhiemVuNamService{
	@Autowired
	NhiemVuNamRepo repo;

	@Override
	public NhiemVuNam save(NhiemVuNam nhiemVuNam) {
		// TODO Auto-generated method stub
		return repo.save(nhiemVuNam);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<NhiemVuNam> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public List<NhiemVuNam> findByKeHoachNamIdAndDaXoa(Long keHoachNamId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByKeHoachNamIdAndDaXoa(keHoachNamId, daXoa);
	}

	@Override
	public int setFixedDaXoaForKeHoachNamId(Boolean daXoa, Long keHoachNamId) {
		// TODO Auto-generated method stub
		return repo.setFixedDaXoaForKeHoachNamId(daXoa, keHoachNamId);
	}

	@Override
	public List<NhiemVuNam> findByKeHoachNamIdAndNhiemVuChaIdIsNullAndDaXoa(Long keHoachNamId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByKeHoachNamIdAndNhiemVuChaIdIsNullAndDaXoa(keHoachNamId, daXoa);
	}

	@Override
	public List<NhiemVuNam> findByKeHoachNamIdAndNhiemVuChaIdAndDaXoa(Long keHoachNamId, Long nhiemVuChaId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByKeHoachNamIdAndNhiemVuChaIdAndDaXoa(keHoachNamId, nhiemVuChaId, daXoa);
	}

	@Override
	public List<NhiemVuNam> findByKeHoachNamIdAndLoaiNhiemVuIdAndNhiemVuChaIdIsNullAndDaXoa(Long keHoachNamId, Long loaiNhiemVuId,
			Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByKeHoachNamIdAndLoaiNhiemVuIdAndNhiemVuChaIdAndDaXoa(keHoachNamId, loaiNhiemVuId, loaiNhiemVuId, daXoa);
	}

	@Override
	public List<NhiemVuNam> findByKeHoachNamIdAndLoaiNhiemVuIdAndNhiemVuChaIdAndDaXoa(Long keHoachNamId, Long loaiNhiemVuId,
			Long nhiemVuChaId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByKeHoachNamIdAndLoaiNhiemVuIdAndNhiemVuChaIdAndDaXoa(keHoachNamId, loaiNhiemVuId, nhiemVuChaId, daXoa);
	}

	@Override
	public Page<NhiemVuNam> findAll(Long donViChuTriId, List<Integer> tinhTrangs, Integer nam, Long keHoachNamId,
			LocalDate tuNgay, LocalDate denNgay, String tenNhiemVu, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(NhiemVuNamSpecifications.quichSearch(donViChuTriId, tinhTrangs, nam, keHoachNamId, tuNgay, denNgay,
				tenNhiemVu), pageable);
	}

	@Override
	public List<NhiemVuNam> getThongKeSoLuong(Long donViChuTriId, Integer nam, Long keHoachNamId, List<Integer> tinhTrangs,
			LocalDate tuNgay, LocalDate denNgay, String tenNhiemVu) {
		// TODO Auto-generated method stub
		return repo.findAll(NhiemVuNamSpecifications.thongke(donViChuTriId, nam, keHoachNamId, tinhTrangs, tuNgay, denNgay, tenNhiemVu));
	}

}
