package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmTrinhDo;

public interface DmTrinhDoService {
	public DmTrinhDo save(DmTrinhDo entity);

	public void deleteById(Long id);

	public Optional<DmTrinhDo> findById(Long id);

	public Page<DmTrinhDo> findAll(String search, Integer trangThai, Pageable pageable);
}
