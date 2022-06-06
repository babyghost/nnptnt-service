package vn.dnict.microservice.nnptnt.kehoach.danhsachkehoach.dao.sevice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.dnict.microservice.nnptnt.kehoach.danhsachkehoach.dao.model.DanhSachKeHoach;
import vn.dnict.microservice.nnptnt.kehoach.danhsachkehoach.dao.sevice.DanhSachKeHoachService;

@Service
public class DanhSachKeHoachServiceImpl implements DanhSachKeHoachService {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")

	public List<DanhSachKeHoach> findAll(String tenKeHoach, Integer nam, Long phongBanId, Long donViId, Long nguoiNhanId, Integer loaiKeHoach,
			Integer trangThai, Boolean isDieuChinh, Boolean isBanHanh) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder queryString = new StringBuilder(
				"SELECT * FROM view_kehoach WHERE ((:phongBanId = ANY(phongbanthuchien_ids) OR phongbanlapkh_id = :phongBanId) OR (:donViId = ANY(donvithuchien_ids) OR donvilapkh_id = :donViId) OR :nguoiNhanId = ANY(nguoinhan_ids))");
		
		if (tenKeHoach != null && !tenKeHoach.isEmpty()) {
			queryString.append(" AND (UPPER(tenkehoach) LIKE :tenKeHoach OR vn_unaccent(UPPER(tenkehoach)) LIKE :tenKeHoach)");
			params.put("tenKeHoach", "%" + tenKeHoach.toUpperCase().trim() + "%");
		}
		
		if (nam != null && nam > 0) {
			queryString.append(" AND nam = :nam");
			params.put("nam", nam);
		}
		
		if (loaiKeHoach != null && loaiKeHoach > 0) {
			queryString.append(" AND loaikehoach = :loaiKeHoach");
			params.put("loaiKeHoach", loaiKeHoach);
		}
		
		if (Boolean.TRUE.equals(isDieuChinh)) {
			queryString.append(" AND ((is_banhanh = true AND (is_dieuchinh IS NULL OR is_dieuchinh = false)) OR is_dieuchinh = true)");
		}
		
		if (isBanHanh != null) {
			queryString.append(" AND is_banhanh = :isBanHanh");
			params.put("isBanHanh", isBanHanh);
		}
		
		if (trangThai != null && trangThai > 0) {
			queryString.append(" AND trangthai = :trangThai");
			params.put("trangThai", trangThai);
		}
		
		params.put("phongBanId", phongBanId);
		params.put("donViId", donViId);
		params.put("nguoiNhanId", nguoiNhanId);
		Query q = em.createNativeQuery(queryString.toString(), "DanhSachKeHoach");
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			q.setParameter(entry.getKey(), entry.getValue());
		}
		return q.getResultList();
	}
	
}
