package vn.dnict.microservice.core.dao.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.dnict.microservice.core.dao.model.DanhSachCoreChucNang;

@Service
public class DanhSachCoreChucNangServiceImpl implements DanhSachCoreChucNangService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<DanhSachCoreChucNang> getRouters(List<String> roleNames) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(Objects.isNull(roleNames) || roleNames.isEmpty()) {
			roleNames = new ArrayList<String>();
			roleNames.add("ROLE_GUEST");
		}
		String[] itemsArray = new String[roleNames.size()];
        itemsArray = roleNames.toArray(itemsArray);
		String queryString = "WITH RECURSIVE expression1 AS (\r\n" + 
				"	SELECT\r\n" + 
				"		cn.ID,\r\n" + 
				"		cn.nhomchucnangcha_id,\r\n" + 
				"		cn.ten,\r\n" + 
				"		cn.ma,\r\n" + 
				"		cn.icon,\r\n" + 
				"		cn.PATH,\r\n" + 
				"		cn.LEVEL,\r\n" + 
				"		cn.sapxep,\r\n" + 
				"		cn.path_info,\r\n" + 
				"		cn.chucnang_id,\r\n" + 
				"		cn.chucnang_ten,\r\n" + 
				"		cn.chucnang_ma,\r\n" + 
				"		cn.chucnang_path,\r\n" + 
				"		cn.component,\r\n" + 
				"		cn.hidden,\r\n" + 
				"		cn.alwaysshow,\r\n" + 
				"		cn.nocache,\r\n" + 
				"		cn.affix,\r\n" + 
				"		cn.breadcrumb,\r\n" + 
				"		cn.activemenu,\r\n" + 
				"		cn.chucnang_sapxep,\r\n" + 
				"		cn.roles \r\n" + 
				"	FROM\r\n" + 
				"		view_corechucnang cn \r\n" + 
				"	WHERE\r\n" + 
				"		:roleNames\\:\\:text[] && roles \r\n" + 
				"	GROUP BY\r\n" + 
				"		cn.ID,\r\n" + 
				"		cn.nhomchucnangcha_id,\r\n" + 
				"		cn.ten,\r\n" + 
				"		cn.ma,\r\n" + 
				"		cn.icon,\r\n" + 
				"		cn.PATH,\r\n" + 
				"		cn.LEVEL,\r\n" + 
				"		cn.sapxep,\r\n" + 
				"		cn.path_info,\r\n" + 
				"		cn.chucnang_id,\r\n" + 
				"		cn.chucnang_ten,\r\n" + 
				"		cn.chucnang_ma,\r\n" + 
				"		cn.chucnang_path,\r\n" + 
				"		cn.component,\r\n" + 
				"		cn.hidden,\r\n" + 
				"		cn.alwaysshow,\r\n" + 
				"		cn.nocache,\r\n" + 
				"		cn.affix,\r\n" + 
				"		cn.breadcrumb,\r\n" + 
				"		cn.activemenu,\r\n" + 
				"		cn.chucnang_sapxep,\r\n" + 
				"		cn.roles UNION\r\n" + 
				"	SELECT A\r\n" + 
				"		.ID,\r\n" + 
				"		A.nhomchucnangcha_id,\r\n" + 
				"		A.ten,\r\n" + 
				"		A.ma,\r\n" + 
				"		A.icon,\r\n" + 
				"		A.PATH,\r\n" + 
				"		A.LEVEL,\r\n" + 
				"		A.sapxep,\r\n" + 
				"		A.path_info,\r\n" + 
				"		A.chucnang_id,\r\n" + 
				"		A.chucnang_ten,\r\n" + 
				"		A.chucnang_ma,\r\n" + 
				"		A.chucnang_path,\r\n" + 
				"		A.component,\r\n" + 
				"		A.hidden,\r\n" + 
				"		A.alwaysshow,\r\n" + 
				"		A.nocache,\r\n" + 
				"		A.affix,\r\n" + 
				"		A.breadcrumb,\r\n" + 
				"		A.activemenu,\r\n" + 
				"		A.chucnang_sapxep,\r\n" + 
				"		A.roles \r\n" + 
				"	FROM\r\n" + 
				"		view_corechucnang\r\n" + 
				"		A JOIN expression1 ON expression1.nhomchucnangcha_id = A.ID \r\n" + 
				"	GROUP BY\r\n" + 
				"		A.ID,\r\n" + 
				"		A.nhomchucnangcha_id,\r\n" + 
				"		A.ten,\r\n" + 
				"		A.ma,\r\n" + 
				"		A.icon,\r\n" + 
				"		A.PATH,\r\n" + 
				"		A.LEVEL,\r\n" + 
				"		A.sapxep,\r\n" + 
				"		A.path_info,\r\n" + 
				"		A.chucnang_id,\r\n" + 
				"		A.chucnang_ten,\r\n" + 
				"		A.chucnang_ma,\r\n" + 
				"		A.chucnang_path,\r\n" + 
				"		A.component,\r\n" + 
				"		A.hidden,\r\n" + 
				"		A.alwaysshow,\r\n" + 
				"		A.nocache,\r\n" + 
				"		A.affix,\r\n" + 
				"		A.breadcrumb,\r\n" + 
				"		A.activemenu,\r\n" + 
				"		A.chucnang_sapxep,\r\n" + 
				"		A.roles \r\n" + 
				"	) SELECT\r\n" + 
				"	* \r\n" + 
				"FROM\r\n" + 
				"	expression1 \r\n" + 
				"ORDER BY\r\n" + 
				"	path_info, chucnang_sapxep";
		params.put("roleNames", itemsArray);
		System.out.println("roleNames:" + roleNames);
		Query q = em.createNativeQuery(queryString, "DanhSachCoreChucNang");
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			q.setParameter(entry.getKey(), entry.getValue());
		}
		@SuppressWarnings("unchecked")
		List<DanhSachCoreChucNang> list = q.getResultList();
		if (list == null || list.isEmpty()) {
			list = new ArrayList<DanhSachCoreChucNang>();
		}
		return list;
	}

}
