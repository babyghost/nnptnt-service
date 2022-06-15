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
	public NhiemVuNam save(NhiemVuNam entity) {
		// TODO Auto-generated method stub
		return repo.save(entity);
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
	public Page<NhiemVuNam> findAll(Long donViChuTriId, Long keHoachNamId, Integer nam, Integer tinhTrang, String tenNhiemVu,
			LocalDate tuNgay, LocalDate denNgay, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(NhiemVuNamSpecifications.quickSearch(donViChuTriId, keHoachNamId, nam, tinhTrang, tenNhiemVu, tuNgay,
				denNgay), pageable);
	}
	
	@Override
	public Page<NhiemVuNam> tongHopKeHoachNam( Long donViChuTriId, Long keHoachNamId, Integer nam, Integer tinhTrang, String tenNhiemVu,
			LocalDate tuNgay, LocalDate denNgay, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(NhiemVuNamSpecifications.tongHopKeHoachNam(donViChuTriId, keHoachNamId, nam, tinhTrang, tenNhiemVu, tuNgay,
				denNgay), pageable);
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
	public Optional<NhiemVuNam> findByKeHoachNamId(Long keHoachNamId) {
		// TODO Auto-generated method stub
		return repo.findByKeHoachNamId(keHoachNamId);
	}

	@Override
	public List<NhiemVuNam> getByKeHoachNamIdAndNhiemVuChaIdIsNullAndDaXoa(Long keHoachNamId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.getByKeHoachNamIdAndNhiemVuChaIdIsNullAndDaXoa(keHoachNamId, daXoa);
	}

	@Override
	public List<NhiemVuNam> getByKeHoachNamIdAndNhiemVuChaIdAndDaXoa(Long keHoachNamId, Long nhiemVuCha, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.getByKeHoachNamIdAndNhiemVuChaIdAndDaXoa(keHoachNamId, nhiemVuCha, daXoa);
	}

}
