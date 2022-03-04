package vn.dnict.microservice.nnptnt.qlchomeo.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.ThongTinChoMeoImport;

public interface ThongTinChoMeoImportService {
	public ThongTinChoMeoImport save(ThongTinChoMeoImport entity);

	public void deleteById(Long id);

	public Optional<ThongTinChoMeoImport> findById(Long id);

	public Page<ThongTinChoMeoImport> findAll(String search, Long thongTinChoMeoId, String trangThai, Pageable pageable);
}
