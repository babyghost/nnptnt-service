package vn.dnict.microservice.nnptnt.qlchomeo.dao.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.ThongTinChoMeoImport;

@Repository
public interface ThongTinChoMeoImportRepo extends JpaRepository<ThongTinChoMeoImport, Long>, JpaSpecificationExecutor<ThongTinChoMeoImport>{

}
