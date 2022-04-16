package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.dao.model.ThongTinChoMeoImport;

public interface ThongTinChoMeoImportService {
	public ThongTinChoMeoImport save(ThongTinChoMeoImport thongTinChoMeoImport);

	public void deleteById(Long id);

	public Optional<ThongTinChoMeoImport> findById(Long id);

	public Page<ThongTinChoMeoImport> findAll( Long thongTinChoMeoId, String trangThai, String chuHo, String dienThoai, String loaiDongVat, String giong, Pageable pageable);
}
