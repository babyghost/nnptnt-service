package vn.dnict.microservice.nnptnt.dm.nganhnghe.dao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.nnptnt.dm.nganhnghe.dao.model.DmNganhNgheOCOP;
import vn.dnict.microservice.nnptnt.dm.nganhnghe.dao.service.DmNganhNgheOCOPService;
import vn.dnict.microservice.nnptnt.dm.nganhnghe.dao.service.DmNganhNgheOCOPSpecifications;
import vn.dnict.microservice.nnptnt.dm.nganhnghe.dao.service.repo.DmNganhNgheOCOPRepo;
@Service
@Transactional
public class DmNganhNgheOCOPServiceImpl implements DmNganhNgheOCOPService {

		@Autowired
		DmNganhNgheOCOPRepo repo;

		@Override
		public DmNganhNgheOCOP save(DmNganhNgheOCOP entity) {
			// TODO Auto-generated method stub
			return repo.save(entity);
		}

		@Override
		public void deleteById(Long id) {
			// TODO Auto-generatded method stub
			repo.deleteById(id);
		}

		@Override
		public Optional<DmNganhNgheOCOP> findById(Long id) {
			// TODO Auto-generated method stub
			return repo.findById(id);
		}

		@Override
		public Page<DmNganhNgheOCOP> findAll(String ten, Integer trangThai, Pageable pageable) {
			// TODO Auto-generated method stub
			return repo.findAll(DmNganhNgheOCOPSpecifications.quickSearch(ten, trangThai),pageable);
		}


}
