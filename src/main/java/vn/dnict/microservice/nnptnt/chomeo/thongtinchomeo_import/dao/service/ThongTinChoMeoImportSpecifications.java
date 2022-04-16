package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.dao.model.ThongTinChoMeoImport;

public class ThongTinChoMeoImportSpecifications {
	public static Specification<ThongTinChoMeoImport> quickSearch( final Long thongTinChoMeoId,
			final String trangThai,final String chuHo,final String dienThoai,final String loaiDongVat,final String giong) {
		return new Specification<ThongTinChoMeoImport>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<ThongTinChoMeoImport> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
//				if (search != null && !search.isEmpty()) {
//					Predicate chuHo = cb.like(cb.lower(root.<String>get("chuHo")), "%" + search.toLowerCase() + "%");
//					predicates.add(cb.or(chuHo));
//				}
				if (chuHo != null && !chuHo.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("chuHo")), "%" + chuHo.trim().toLowerCase() + "%"));
				}
				if(dienThoai != null && !dienThoai.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("dienThoai")), "%" + dienThoai.trim().toLowerCase() + "%"));
				}
				if(loaiDongVat != null && !loaiDongVat.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("loaiDongVat")), "%" + loaiDongVat.trim().toLowerCase() + "%"));
				}
				if(giong != null && !giong.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("giong")), "%" + giong.trim().toLowerCase() + "%"));
				}
				
				if (thongTinChoMeoId != null) {
					predicates.add(cb.equal(root.<String>get("thongTinChoMeoId"), thongTinChoMeoId));
				}
				if(trangThai != null && !trangThai.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("trangThai")), "%" + giong.trim().toLowerCase() + "%"));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
