package vn.dnict.microservice.nnptnt.hopdong.baocaothongke.dao.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.hopdong.baocaothongke.dao.model.BaoCaoThongKe;

public interface BaoCaoThongKeService {
	public Page<BaoCaoThongKe> getBaoCaoThongKe(String tenHopDong, Long loaiHopDongId, String dvthTen,
			String cnthTen, LocalDate tuThanhToanNgay, LocalDate denThanhToanNgay, Integer trangThai,
			Pageable pageable);
}