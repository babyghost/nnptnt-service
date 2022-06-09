package vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.model.CoSoGietMo;

@Service
public interface CoSoGietMoService {
	public CoSoGietMo save(CoSoGietMo entity);

	public void deleteById(Long id);

	public Optional<CoSoGietMo> findById(Long id);
	
	public Optional<CoSoGietMo> findByTenCoSo(String tenCoSo);

	public Page<CoSoGietMo> findAll(String tenCoSo, String tenChuCoSo, String dienThoai, String email, 
			Long phuongXaId, Long quanHuyenId, Pageable pageable);
}
