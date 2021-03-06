package vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.model.KeHoachTiemPhong;
@Repository
public interface KeHoachTiemPhongRepo extends JpaRepository<KeHoachTiemPhong, Long>,JpaSpecificationExecutor<KeHoachTiemPhong>{

}
