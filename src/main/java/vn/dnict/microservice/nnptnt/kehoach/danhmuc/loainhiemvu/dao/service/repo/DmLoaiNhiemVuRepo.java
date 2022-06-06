package vn.dnict.microservice.nnptnt.kehoach.danhmuc.loainhiemvu.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.danhmuc.loainhiemvu.dao.model.DmLoaiNhiemVu;

@Repository
public interface DmLoaiNhiemVuRepo extends JpaRepository<DmLoaiNhiemVu, Long>, JpaSpecificationExecutor<DmLoaiNhiemVu> {

	List<DmLoaiNhiemVu> findByMaIgnoreCaseAndDaXoa(String ma, boolean daXoa);

	List<DmLoaiNhiemVu> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, boolean daXoa);
}
