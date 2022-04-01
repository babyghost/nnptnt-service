package vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.model.KeHoach2ChoMeo;

@Repository
public interface KeHoach2ChoMeoRepo extends JpaRepository<KeHoach2ChoMeo, Long>, JpaSpecificationExecutor<KeHoach2ChoMeo>{
	
	
	public List<KeHoach2ChoMeo> findByThongTinChoMeoIdAndDaXoa(Long thongTinChoMeoId, Boolean daXoa);
}
