package vn.dnict.microservice.core.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.core.dao.model.CoreCauHinhDanhSach;

public class CoreCauHinhDanhSachSpecifications {
	public static Specification<CoreCauHinhDanhSach> quickSearch(final String maDanhSach, final String tenCot,
			final Boolean trangThai) {

		return new Specification<CoreCauHinhDanhSach>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -7556941560367332930L;

			@Override
			public Predicate toPredicate(Root<CoreCauHinhDanhSach> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (maDanhSach != null && !maDanhSach.isEmpty()) {
					predicates.add(cb.equal(root.<String>get("maDanhSach"), maDanhSach));
				}
				if (tenCot != null && !tenCot.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenCot")), "%" + tenCot.toLowerCase() + "%"));
				}
				predicates.add(cb.equal(root.<Boolean>get("trangThai"), trangThai));
				if (predicates.size() > 0) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}