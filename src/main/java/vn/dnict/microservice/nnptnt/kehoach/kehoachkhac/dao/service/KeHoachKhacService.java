package vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.dao.model.KeHoachKhac;

public interface KeHoachKhacService {

	KeHoachKhac save(KeHoachKhac khKeHoachKhac);

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<KeHoachKhac> findById(Long id);

	Page<KeHoachKhac> findAll(String tenKeHoach, Integer nam, Pageable pageable);

	Integer getMaxNam();

}
