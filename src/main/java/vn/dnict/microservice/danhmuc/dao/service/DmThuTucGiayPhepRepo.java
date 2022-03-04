package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.danhmuc.dao.model.DmThuTucGiayPhep;

@Repository
public interface DmThuTucGiayPhepRepo
		extends JpaRepository<DmThuTucGiayPhep, Long>, JpaSpecificationExecutor<DmThuTucGiayPhep> {

	public List<DmThuTucGiayPhep> findByMaAndDaXoa(String maThuTuc, Integer daXoa);
}
