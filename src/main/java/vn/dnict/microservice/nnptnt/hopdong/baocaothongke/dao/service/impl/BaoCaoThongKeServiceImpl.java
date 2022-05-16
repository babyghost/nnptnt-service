package vn.dnict.microservice.nnptnt.hopdong.baocaothongke.dao.service.impl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.hopdong.baocaothongke.dao.model.BaoCaoThongKe;
import vn.dnict.microservice.nnptnt.hopdong.baocaothongke.dao.service.BaoCaoThongKeService;

@Service
public class BaoCaoThongKeServiceImpl implements BaoCaoThongKeService {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public Page<BaoCaoThongKe> getBaoCaoThongKe(String tenHopDong, Long loaiHopDongId, String dvthTen,
			String cnthTen, LocalDate tuThanhToanNgay, LocalDate denThanhToanNgay, Boolean trangThai,
			Pageable pageable) {
		Map<String, Object> params = new HashMap<String, Object>();
		String queryString = "SELECT * FROM quanlyhopdong";
		String countQueryString = "SELECT COUNT(a) FROM quanlyhopdong a";
		String whereString = " WHERE id > 0";
		if (tenHopDong != null && !tenHopDong.isEmpty()) {
			whereString += " AND UPPER(tenhopdong) LIKE :tenHopDong";
			params.put("tenHopDong", "%" + tenHopDong.toUpperCase() + "%");
		}
		if (loaiHopDongId != null && loaiHopDongId > 0) {
			whereString += " AND loaihopdong_id = :loaiHopDongId";
			params.put("loaiHopDongId", loaiHopDongId);
		}
		if (dvthTen != null && !dvthTen.isEmpty()) {
			whereString += " AND UPPER(dvth_ten) LIKE :dvthTen";
			params.put("dvthTen", "%" + dvthTen.toUpperCase() + "%");
		}
		if (cnthTen != null && !cnthTen.isEmpty()) {
			whereString += " AND UPPER(cnth_ten) LIKE :cnthTen";
			params.put("cnthTen", "%" + cnthTen.toUpperCase() + "%");
		}
		if (trangThai != null) {
			whereString += " AND trangthai = :trangThai";
			params.put("trangThai", trangThai);
		}
		if (tuThanhToanNgay != null) {
			whereString += " AND thanhtoan_ngay <= :tuThanhToanNgay";
			params.put("tuThanhToanNgay", tuThanhToanNgay);
		}
		if (denThanhToanNgay != null) {
			whereString += " AND thanhtoan_ngay >= :denThanhToanNgay";
			params.put("denThanhToanNgay", denThanhToanNgay);
		}
		queryString += whereString;
		countQueryString += whereString;
		Query q = em.createNativeQuery(queryString, "BaoCaoThongKe");
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			q.setParameter(entry.getKey(), entry.getValue());
		}
		q.setMaxResults(pageable.getPageSize());
		q.setFirstResult((int) pageable.getOffset());
		List<BaoCaoThongKe> list = q.getResultList();
		if (list == null || list.isEmpty()) {
			list = new ArrayList<BaoCaoThongKe>();
		}
		Page<BaoCaoThongKe> pages = new PageImpl<BaoCaoThongKe>(list, pageable,
				countTotalRecords(countQueryString, params).intValue());
		return pages;
	}

	@SuppressWarnings("unchecked")
	public BigInteger countTotalRecords(String countQueryString, Map<String, Object> params) {
		Query q = em.createNativeQuery(countQueryString);
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			q.setParameter(entry.getKey(), entry.getValue());
		}
		BigInteger totalRow = (BigInteger) q.getResultList().stream().findFirst().orElse(0);
		return totalRow;
	}

}
