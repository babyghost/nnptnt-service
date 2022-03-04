package vn.dnict.microservice.nnptnt.qlvatnuoi.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.NamChanNuoi;



@Service
public interface NamChanNuoiService {

	public NamChanNuoi save(NamChanNuoi entity);

	public void deleteById(Long id);

//	public static Optional<NamChanNuoi> findById(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	public Optional<NamChanNuoi> findById(Long id);
	
	
	
	public Page<NamChanNuoi> findAll(String search, Integer Quy, Pageable pageable);


}
