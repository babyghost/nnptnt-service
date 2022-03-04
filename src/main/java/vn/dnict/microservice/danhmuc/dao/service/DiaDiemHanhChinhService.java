package vn.dnict.microservice.danhmuc.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.danhmuc.dao.model.DiaDiemHanhChinh;

public interface DiaDiemHanhChinhService {

	public Page<DiaDiemHanhChinh> findAll(String search, Long tinhThanhId, String tinhThanhCode, Long quanHuyenId,
			String quanHuyenCode, Long phuongXaId, String phuongXaCode, Pageable pageable);

	public Optional<DiaDiemHanhChinh> findByPhuongXaId(Long phuongXaId);
}
