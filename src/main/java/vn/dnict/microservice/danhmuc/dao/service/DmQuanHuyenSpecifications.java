package vn.dnict.microservice.danhmuc.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;

public class DmQuanHuyenSpecifications {
	public static Specification<DmQuanHuyen> quickSearch(final String search, final Boolean trangThai,
			final String tinhThanhCode, final Long duAnId) {
		return new Specification<DmQuanHuyen>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<DmQuanHuyen> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (search != null && !search.isEmpty()) {
					Predicate ten = cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase() + "%");
					Predicate ma = cb.like(cb.lower(root.<String>get("ma")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(ten, ma));
				}
				if (trangThai != null) {
					predicates.add(cb.equal(root.<Boolean>get("trangThai"), trangThai));
				}
				if (duAnId != null && duAnId > -1) {
					predicates.add(cb.equal(root.join("qlHoSoCongTrinhDmDuAns").get("id"), duAnId));
				}
				if (tinhThanhCode != null && !tinhThanhCode.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tinhThanhCode")),
							"%" + tinhThanhCode.toLowerCase() + "%"));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
		};
	}
}
