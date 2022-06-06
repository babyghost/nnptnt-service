package vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.dao.model.DmTrangThaiKeHoach;

public interface DmTrangThaiKeHoachService {

	DmTrangThaiKeHoach save(DmTrangThaiKeHoach dmTrangThaiKeHoach);

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<DmTrangThaiKeHoach> findById(Long id);

	Page<DmTrangThaiKeHoach> findAll(String search, Pageable pageable);

	List<DmTrangThaiKeHoach> findByThuHoiIdIsNotNull();

	List<DmTrangThaiKeHoach> findByThuHoiKhtcIdIsNotNull();

}
