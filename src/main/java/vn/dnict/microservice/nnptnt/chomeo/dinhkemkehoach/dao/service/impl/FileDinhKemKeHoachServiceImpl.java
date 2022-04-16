package vn.dnict.microservice.nnptnt.chomeo.dinhkemkehoach.dao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.chomeo.dinhkemkehoach.dao.model.FileDinhKemKeHoach;
import vn.dnict.microservice.nnptnt.chomeo.dinhkemkehoach.dao.service.FileDinhKemKeHoachService;
import vn.dnict.microservice.nnptnt.chomeo.dinhkemkehoach.dao.service.repo.FileDinhKemKeHoachRepo;

@Service
public class FileDinhKemKeHoachServiceImpl implements FileDinhKemKeHoachService {
	@Autowired
	FileDinhKemKeHoachRepo repo;

	@Override
	public FileDinhKemKeHoach save(FileDinhKemKeHoach fileDinhKemKeHoach) {
		// TODO Auto-generated method stub
		return repo.save(fileDinhKemKeHoach);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public Optional<FileDinhKemKeHoach> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}


	@Override
	public List<FileDinhKemKeHoach> findByDinhKemFileIdAndDaXoa(Long dinhKemFileId, Boolean daXoa) {
		// TODO Auto-generated method stub
		return repo.findByDinhKemFileIdAndDaXoa(dinhKemFileId, daXoa);
	}



	@Override
	public List<FileDinhKemKeHoach> findByDinhKemFileIdAndKeHoachTiemPhongId(Long dinhKemFileId,
			Long keHoachTiemPhongId) {
		// TODO Auto-generated method stub
		return repo.findByDinhKemFileIdAndKeHoachTiemPhongId(dinhKemFileId, keHoachTiemPhongId);
	}

	@Override
	public int setFixedDaXoaForKeHoachTiemPhongId(Boolean daXoa, Long keHoachTiemPhongId) {
		// TODO Auto-generated method stub
		return repo.setFixedDaXoaForKeHoachTiemPhongId(daXoa, keHoachTiemPhongId);
	}

	@Override
	public Optional<FileDinhKemKeHoach> findBykeHoachTiemPhongId(Long keHoachTiemPhongId) {
		// TODO Auto-generated method stub
		return repo.findBykeHoachTiemPhongId(keHoachTiemPhongId);
	}

	
}
