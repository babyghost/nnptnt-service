package vn.dnict.microservice.nnptnt.hopdong.baocaothongke.business;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.hopdong.baocaothongke.dao.model.BaoCaoThongKe;
import vn.dnict.microservice.nnptnt.hopdong.baocaothongke.dao.service.BaoCaoThongKeService;

@Service
public class BaoCaoThongKeBusiness {
	@Autowired
	private BaoCaoThongKeService serviceBaoCaoThongKeService;

	public Page<BaoCaoThongKe> baoCaoThongKe(int page, int size, String sortBy, String sortDir,
			String tenHopDong, Long loaiHopDongId, String dvthTen, String cnthTen, LocalDate tuThanhToanNgay,
			LocalDate denThanhToanNgay, Boolean trangThai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;  
		}
		Page<BaoCaoThongKe> pageBaoCaoThongKe = serviceBaoCaoThongKeService.getBaoCaoThongKe(
				tenHopDong, loaiHopDongId, dvthTen, cnthTen, tuThanhToanNgay, denThanhToanNgay, trangThai,
				PageRequest.of(page, size, direction, sortBy));

		return pageBaoCaoThongKe;
	}

}
