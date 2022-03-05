package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.dao.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.dao.model.ThongTinChoMeoImport;

@Repository
public interface ThongTinChoMeoImportRepo extends JpaRepository<ThongTinChoMeoImport, Long>, JpaSpecificationExecutor<ThongTinChoMeoImport>{

}
