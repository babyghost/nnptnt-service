package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model.ThongTinChoMeo;

public interface ThongTinChoMeoService {
	public ThongTinChoMeo save(ThongTinChoMeo entity);

	public void deleteById(Long id);

	public Optional<ThongTinChoMeo> findById(Long id);

	public Page<ThongTinChoMeo> findAll(String search, Integer trangThai, Pageable pageable);
}
