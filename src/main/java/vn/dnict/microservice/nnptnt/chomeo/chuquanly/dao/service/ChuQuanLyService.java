package vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;

public interface ChuQuanLyService {
	public ChuQuanLy save(ChuQuanLy entity);

	public void deleteById(Long id);

	public Optional<ChuQuanLy> findById(Long id);

	public Page<ChuQuanLy> findAll(String chuHo, String diaChi, String dienThoai, Pageable pageable);
	
	public Optional<ChuQuanLy> findByChuHoAndDaXoa(String chuHo, Boolean daXoa);
	
	public List<ChuQuanLy> findByDienThoai(String dienthoai);
	
	public Optional<ChuQuanLy> findByChuHoAndDienThoaiAndDiaChiAndDaXoa(String chuHo, String dienThoai,String diaChi, Boolean daXoa);
}
