package vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;



@Repository
public interface ChuQuanLyRepo extends JpaRepository<ChuQuanLy,Long> , JpaSpecificationExecutor<ChuQuanLy>{
	public Optional<ChuQuanLy> findByChuHoAndDaXoa(String chuHo, Boolean daXoa);
	public List<ChuQuanLy> findByDienThoai(String dienthoai);
	public Optional<ChuQuanLy> findByChuHoAndDienThoaiAndDiaChiAndDaXoa(String chuHo, String dienThoai,String diaChi, Boolean daXoa);
}
