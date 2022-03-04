package vn.dnict.microservice.danhmuc.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.dnict.microservice.danhmuc.dao.model.DmCanBo;

@Repository
public interface DmCanBoRepo extends JpaRepository<DmCanBo, Long>, JpaSpecificationExecutor<DmCanBo> {
	public List<DmCanBo> findByIdIn(List<Long> idList);
	
	public Optional<DmCanBo> findFirstByEmailAndDaXoa(String email, Integer daXoa);

	public List<DmCanBo> findByEmailAndDaXoa(String email, Integer daXoa);

	public List<DmCanBo> findByEmailIgnoreCaseAndDaXoa(String email, Integer daXoa);
	
	public List<DmCanBo> findDsCanBoByPhongBanIdAndDaXoa(Long phongBan, Integer daXoa);
	
	public List<DmCanBo> findDsCanBoByDonViIdAndDaXoa(Long donVi, Integer daXoa);
}
