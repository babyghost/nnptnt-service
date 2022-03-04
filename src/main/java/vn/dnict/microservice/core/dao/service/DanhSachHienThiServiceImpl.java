package vn.dnict.microservice.core.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.dnict.microservice.core.dao.model.DanhSachHienThi;

@Service
public class DanhSachHienThiServiceImpl implements DanhSachHienThiService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<DanhSachHienThi> findByMaDanhSachAndNguoiSuDung(String maDanhSach,
			String nguoiSuDung) {
		String queryString = "SELECT \r\n" + 
				"a.id,\r\n" + 
				"a.madanhsach,\r\n" + 
				"a.tencot,\r\n" + 
				"a.tengiatri,\r\n" + 
				"a.dorongcot,\r\n" + 
				"a.sapxep,\r\n" + 
				"CASE WHEN b.is_hienthi IS NOT NULL THEN b.is_hienthi ELSE TRUE END AS is_hienthi,\r\n" + 
				"b.id AS caidathienthi_id,\r\n" + 
				"b.nguoisudung\r\n" + 
				"FROM core_cauhinhdanhsach a \r\n" + 
				"LEFT JOIN core_caidathienthi b on b.cauhinhdanhsach_id = a.id and b.nguoisudung = '" + nguoiSuDung + "' " +  
				"WHERE a.madanhsach = '" + maDanhSach + "' " +
		//		"AND b.daxoa = false " +
				"ORDER BY sapxep ASC";
		Query q = em.createNativeQuery(queryString, "DanhSachHienThi");
		@SuppressWarnings("unchecked")
		List<DanhSachHienThi> list = q.getResultList();
		if (list == null || list.isEmpty()) {
			list = new ArrayList<DanhSachHienThi>();
		}
		return list;
	}

}
