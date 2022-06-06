package vn.dnict.microservice.nnptnt.kehoach.thuchien2vanbanlienquan.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.kehoach.thuchien2vanbanlienquan.dao.model.ThucHien2VanBanLienQuan;


public interface ThucHien2VanBanLienQuanService {

	ThucHien2VanBanLienQuan save(ThucHien2VanBanLienQuan thucHien2VanBanLienQuan);

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<ThucHien2VanBanLienQuan> findById(Long id);

	int setFixedDaXoaForNhiemVu2ThucHienId(boolean daXoa, Long nhiemVu2ThucHienId);

	List<ThucHien2VanBanLienQuan> findByNhiemVu2ThucHienIdAndDaXoa(Long nhiemVu2ThucHienId, boolean daXoa);

	Optional<ThucHien2VanBanLienQuan> findFirstByNhiemVu2ThucHienIdAndVanBanDinhKemId(Long nhiemVu2ThucHienId, Long vanBanDinhKemId);

}
