package vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.dao.model.DmTrangThaiKeHoach;

@Repository
public interface DmTrangThaiKeHoachRepo extends JpaRepository<DmTrangThaiKeHoach, Long>, JpaSpecificationExecutor<DmTrangThaiKeHoach> {

	List<DmTrangThaiKeHoach> findByThuHoiIdIsNotNull();

	List<DmTrangThaiKeHoach> findByThuHoiKhtcIdIsNotNull();

}
