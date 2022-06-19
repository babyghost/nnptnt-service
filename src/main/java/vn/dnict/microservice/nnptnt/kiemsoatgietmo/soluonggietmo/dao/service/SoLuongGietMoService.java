package vn.dnict.microservice.nnptnt.kiemsoatgietmo.soluonggietmo.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.kiemsoatgietmo.soluonggietmo.dao.model.SoLuongGietMo;

@Service
public interface SoLuongGietMoService {
	public SoLuongGietMo save(SoLuongGietMo soLuongGietMo);
	
	public void deleteById(Long id);
	
	public Optional<SoLuongGietMo> findById(Long id);
	
	public List<SoLuongGietMo> findByThongTinGietMoIdAndDaXoa(Long thongTinGietMoId, Boolean daXoa);
	
	public int setFixedDaXoaAndThongTinGietMoId(Boolean daXoa, Long thongTinGietMoId);
	
	public Optional<SoLuongGietMo> findByThongTinGietMoId(Long thongTinGietMoId);
}
