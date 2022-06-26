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
	public List<NhiemVuNam> findByKeHoachIdAndDaXoa(Long keHoachId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByKeHoachIdAndDaXoa(keHoachId, daXoa);
	}

	@Override
	public int setFixedDaXoaForKeHoachId(Boolean daXoa, Long keHoachId) {
		// TODO Auto-generated method stub
		return repo.setFixedDaXoaForKeHoachId(daXoa, keHoachId);
	}

	@Override
	public List<NhiemVuNam> findByKeHoachIdAndNhiemVuChaIdIsNullAndDaXoa(Long keHoachId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByKeHoachIdAndNhiemVuChaIdIsNullAndDaXoa(keHoachId, daXoa);
	}

	@Override
	public List<NhiemVuNam> findByKeHoachIdAndNhiemVuChaIdAndDaXoa(Long keHoachId, Long nhiemVuChaId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByKeHoachIdAndNhiemVuChaIdAndDaXoa(keHoachId, nhiemVuChaId, daXoa);
	}

	@Override
	public List<NhiemVuNam> findByKeHoachIdAndLoaiNhiemVuIdAndNhiemVuChaIdIsNullAndDaXoa(Long keHoachId, Long loaiNhiemVuId,
			Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByKeHoachIdAndLoaiNhiemVuIdAndNhiemVuChaIdAndDaXoa(keHoachId, loaiNhiemVuId, loaiNhiemVuId, daXoa);
	}

	@Override
	public List<NhiemVuNam> findByKeHoachIdAndLoaiNhiemVuIdAndNhiemVuChaIdAndDaXoa(Long keHoachId, Long loaiNhiemVuId,
			Long nhiemVuChaId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByKeHoachIdAndLoaiNhiemVuIdAndNhiemVuChaIdAndDaXoa(keHoachId, loaiNhiemVuId, nhiemVuChaId, daXoa);
	}

	@Override
	public Page<NhiemVuNam> findAll(Long donViChuTriId, Integer tinhTrang, Integer nam, Long keHoachId,
			LocalDate tuNgay, LocalDate denNgay, String tenNhiemVu, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(NhiemVuNamSpecifications.quichSearch(donViChuTriId, tinhTrang, nam, keHoachId, tuNgay, denNgay,
				tenNhiemVu), pageable);
	}

	@Override
	public Page<NhiemVuNam> getThongKeSoLuong(Long donViChuTriId, Integer nam, Long keHoachId, List<Integer> tinhTrangs,
			LocalDate tuNgay, LocalDate denNgay, String tenNhiemVu, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(NhiemVuNamSpecifications.thongke(donViChuTriId, nam, keHoachId, tinhTrangs, tuNgay, denNgay,
				tenNhiemVu), pageable);
	}

}
