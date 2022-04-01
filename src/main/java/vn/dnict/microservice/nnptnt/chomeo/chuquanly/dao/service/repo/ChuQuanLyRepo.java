package vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;



@Repository
public interface ChuQuanLyRepo extends JpaRepository<ChuQuanLy,Long> , JpaSpecificationExecutor<ChuQuanLy>{
	Optional<ChuQuanLy> findByChuHoAndDiaChi(String chuHo, String diaChi);
	List<ChuQuanLy> findByDienThoai(String dienthoai);
}
