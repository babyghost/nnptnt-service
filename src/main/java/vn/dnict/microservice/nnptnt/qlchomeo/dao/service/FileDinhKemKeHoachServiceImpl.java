package vn.dnict.microservice.nnptnt.qlchomeo.dao.service;

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

import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.FileDinhKemKeHoach;

@Service
public class FileDinhKemKeHoachServiceImpl implements FileDinhKemKeHoachService {

	@PersistenceContext
	private EntityManager em;

	public Page<FileDinhKemKeHoach> findAll(String search, Long fileDinhKemId, Long keHoachTiemPhongId, Pageable pageable) {
		Map<String, Object> params = new HashMap<String, Object>();

		String queryString = "SELECT * FROM view_dsFileDinhKemKeHoach_sgtvt WHERE keHoachTiemPhongId > 0";
		String whereString = "";

		if (search != null && !search.isEmpty()) {
			whereString += " AND (UPPER(kehoachtiemphong) LIKE :search )";
			params.put("search", "%" + search.toUpperCase() + "%");
		}
		
		if (keHoachTiemPhongId != null && keHoachTiemPhongId > 0) {
			whereString += " AND kehoachtiemphongid = :keHoachTiemPhongId";
			params.put("keHoachTiemPhongId", keHoachTiemPhongId);

		if (fileDinhKemId != null && fileDinhKemId > 0) {
			whereString += " AND filedinhkemid = :fileDinhKemId";
			params.put("fileDinhKemId", fileDinhKemId);
		}
		
		
		queryString += whereString;
		String countQueryString = "SELECT COUNT(*) FROM( " + queryString + " ) AS count";
		Query q = em.createNativeQuery(queryString, "FileDinhKemKeHoach");
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			q.setParameter(entry.getKey(), entry.getValue());
		}
		q.setMaxResults(pageable.getPageSize());
		q.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		@SuppressWarnings("unchecked")
		List<FileDinhKemKeHoach> list = q.getResultList();
		if (list == null || list.isEmpty()) {
			list = new ArrayList<FileDinhKemKeHoach>();
		}
		Page<FileDinhKemKeHoach> pages = new PageImpl<FileDinhKemKeHoach>(list, pageable,
				countTotalRecords(countQueryString, params).longValue());
		return pages;
	}
		return null;
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
	public Optional<FileDinhKemKeHoach> findByKeHoachTiemPhongId(Long keHoachTiemPhongId) {
		Map<String, Object> params = new HashMap<String, Object>();
		String queryString = "SELECT * FROM view_dsfileDinhKemKeHoach_sgtvt WHERE kehoachtiemphongid = :keHoachTiemPhongId";
		params.put("keHoachTiemPhongId", keHoachTiemPhongId);
		Query q = em.createNativeQuery(queryString, "FileDinhKemKeHoach");
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			q.setParameter(entry.getKey(), entry.getValue());
		}
		return q.getResultList().stream().findFirst();
	}
}
