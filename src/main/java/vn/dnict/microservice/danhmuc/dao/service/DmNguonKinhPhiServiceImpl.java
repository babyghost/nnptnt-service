package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.danhmuc.dao.model.DmNguonKinhPhi;

@Service
public class DmNguonKinhPhiServiceImpl implements DmNguonKinhPhiService {
	@Autowired
	private DmNguonKinhPhiRepo repo;

	public DmNguonKinhPhi save(DmNguonKinhPhi nguonKinhPhi) {
		return repo.save(nguonKinhPhi);
	}

	public Optional<DmNguonKinhPhi> findById(Long id) {
		return repo.findById(id);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Page<DmNguonKinhPhi> findAll(String search, Boolean trangThai, Long id, Long chaId, Pageable pageable) {
		return repo.findAll(DmNguonKinhPhiSpecifications.quickSearch(search, trangThai, id, chaId), pageable);
	}

	public List<DmNguonKinhPhi> findByIdIn(List<Long> idList) {
		return repo.findByIdIn(idList);
	}

	public List<DmNguonKinhPhi> findByTrangThaiAndDaXoa(boolean trangThai, boolean daXoa) {
		return repo.findByTrangThaiAndDaXoa(trangThai, daXoa);
	}

	public List<DmNguonKinhPhi> getDmNguonKinhPhis(Long yeuCau2DonViId) {
		return repo.getDmNguonKinhPhis(yeuCau2DonViId);
	}

	public Double getTongTienDmNguonKinhPhis(Long yeuCau2DonViId, Long dmNguonKinhPhiId) {
		return repo.getTongTienDmNguonKinhPhis(yeuCau2DonViId, dmNguonKinhPhiId);
	}

	public List<DmNguonKinhPhi> getDmNguonKinhPhisByKeHoachKhacId(Long keHoachKhacId) {
		return repo.getDmNguonKinhPhisByKeHoachKhacId(keHoachKhacId);
	}

}
