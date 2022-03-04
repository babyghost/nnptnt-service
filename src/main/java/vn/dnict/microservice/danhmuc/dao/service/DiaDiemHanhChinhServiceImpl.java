package vn.dnict.microservice.danhmuc.dao.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.danhmuc.dao.model.DiaDiemHanhChinh;

@Service
public class DiaDiemHanhChinhServiceImpl implements DiaDiemHanhChinhService {

	@PersistenceContext
	private EntityManager em;

	public Page<DiaDiemHanhChinh> findAll(String search, Long tinhThanhId, String tinhThanhCode, Long quanHuyenId,
			String quanHuyenCode, Long phuongXaId, String phuongXaCode, Pageable pageable) {
		Map<String, Object> params = new HashMap<String, Object>();

		String queryString = "SELECT * FROM view_dsdiadiemhanhchinh_sgtvt WHERE phuongxaid > 0";
		String whereString = "";

		if (search != null && !search.isEmpty()) {
			whereString += " AND (UPPER(phuongxa) LIKE :search OR UPPER(quanhuyen) LIKE :search OR  UPPER(tinhthanh) LIKE :search)";
			params.put("search", "%" + search.toUpperCase() + "%");
		}
		
		if (tinhThanhId != null && tinhThanhId > 0) {
			whereString += " AND tinhthanhid = :tinhThanhId";
			params.put("tinhThanhId", tinhThanhId);
		}
		if (quanHuyenId != null && quanHuyenId > 0) {
			whereString += " AND quanhuyenid = :quanHuyenId";
			params.put("quanHuyenId", quanHuyenId);
		}
		if (phuongXaId != null && phuongXaId > 0) {
			whereString += " AND phuongxaid = :phuongXaId";
			params.put("phuongXaId", phuongXaId);
		}
		if (tinhThanhCode != null && !tinhThanhCode.isEmpty()) {
			whereString += " AND matinhthanh = :tinhThanhCode";
			params.put("tinhThanhCode", tinhThanhCode);
		}
		if (quanHuyenCode != null && !quanHuyenCode.isEmpty()) {
			whereString += " AND maquanhuyen = :quanHuyenCode";
			params.put("quanHuyenCode", quanHuyenCode);
		}
		if (phuongXaCode != null && !phuongXaCode.isEmpty()) {
			whereString += " AND maphuongxa = :phuongXaCode";
			params.put("phuongXaCode", phuongXaCode);
		}
		queryString += whereString;
		String countQueryString = "SELECT COUNT(*) FROM( " + queryString + " ) AS count";
		Query q = em.createNativeQuery(queryString, "DiaDiemHanhChinh");
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			q.setParameter(entry.getKey(), entry.getValue());
		}
		q.setMaxResults(pageable.getPageSize());
		q.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		@SuppressWarnings("unchecked")
		List<DiaDiemHanhChinh> list = q.getResultList();
		if (list == null || list.isEmpty()) {
			list = new ArrayList<DiaDiemHanhChinh>();
		}
		Page<DiaDiemHanhChinh> pages = new PageImpl<DiaDiemHanhChinh>(list, pageable,
				countTotalRecords(countQueryString, params).longValue());
		return pages;
	}

	@SuppressWarnings("unchecked")
	public BigInteger countTotalRecords(String countQueryString, Map<String, Object> params) {
		Query q = em.createNativeQuery(countQueryString);
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			q.setParameter(entry.getKey(), entry.getValue());
		}
		BigInteger totalRow = (BigInteger) q.getResultList().stream().findFirst().orElse(BigInteger.valueOf(0L));
		return totalRow;
	}

	@SuppressWarnings("unchecked")
	public Optional<DiaDiemHanhChinh> findByPhuongXaId(Long phuongXaId) {
		Map<String, Object> params = new HashMap<String, Object>();
		String queryString = "SELECT * FROM view_dsdiadiemhanhchinh_sgtvt WHERE phuongxaid = :phuongXaId";
		params.put("phuongXaId", phuongXaId);
		Query q = em.createNativeQuery(queryString, "DiaDiemHanhChinh");
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			q.setParameter(entry.getKey(), entry.getValue());
		}
		return q.getResultList().stream().findFirst();
	}

}
