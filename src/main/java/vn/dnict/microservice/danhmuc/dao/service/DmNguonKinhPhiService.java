package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DmNguonKinhPhi;

public interface DmNguonKinhPhiService {
	public DmNguonKinhPhi save(DmNguonKinhPhi dmNguonKinhPhi);

	public Optional<DmNguonKinhPhi> findById(Long id);

	public void delete(Long id);

	public Page<DmNguonKinhPhi> findAll(String search, Boolean trangThai, Long id, Long chaId, Pageable pageable);

	public List<DmNguonKinhPhi> findByIdIn(List<Long> idList);

	public List<DmNguonKinhPhi> findByTrangThaiAndDaXoa(boolean trangThai, boolean daXoa);

	public List<DmNguonKinhPhi> getDmNguonKinhPhis(Long yeuCau2DonViId);

	public Double getTongTienDmNguonKinhPhis(Long yeuCau2DonViId, Long dmNguonKinhPhiId);

	public List<DmNguonKinhPhi> getDmNguonKinhPhisByKeHoachKhacId(Long keHoachKhacId);

}
