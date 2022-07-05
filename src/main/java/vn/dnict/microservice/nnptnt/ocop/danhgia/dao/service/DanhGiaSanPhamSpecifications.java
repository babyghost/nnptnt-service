package vn.dnict.microservice.nnptnt.ocop.danhgia.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.ocop.danhgia.dao.model.DanhGiaSanPham;

public class DanhGiaSanPhamSpecifications {
	public static Specification<DanhGiaSanPham> quickSearch(
			final Long sanPhamId, final Long phanHangId) {
		return new Specification<DanhGiaSanPham>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<DanhGiaSanPham> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				
				if (sanPhamId != null) {
					predicates.add(cb.equal(root.<String>get("sanPhamId"), sanPhamId));
				}
				if (phanHangId != null) {
					predicates.add(cb.equal(root.<String>get("phanHangId"), phanHangId));
				}
			

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
