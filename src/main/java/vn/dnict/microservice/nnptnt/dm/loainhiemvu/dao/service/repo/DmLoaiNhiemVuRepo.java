package vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.model.DmLoaiNhiemVu;

@Repository
public interface DmLoaiNhiemVuRepo extends JpaRepository<DmLoaiNhiemVu, Long>, JpaSpecificationExecutor<DmLoaiNhiemVu> {

	public List<DmLoaiNhiemVu> findByMaIgnoreCaseAndDaXoa(String ma, Boolean daXoa);

	public List<DmLoaiNhiemVu> findByIdAndMaIgnoreCaseAndDaXoa(long id, String ma, Boolean daXoa);

	public List<DmLoaiNhiemVu> findByTrangThaiAndDaXoaOrderBySapXepAsc(Boolean trangThai, Boolean daXoa);

}
