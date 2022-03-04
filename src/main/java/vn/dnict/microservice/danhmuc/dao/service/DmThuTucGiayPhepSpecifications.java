package vn.dnict.microservice.danhmuc.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.danhmuc.dao.model.DmThuTucGiayPhep;

public class DmThuTucGiayPhepSpecifications {
	public static Specification<DmThuTucGiayPhep> quickSearch(final String search) {
		return new Specification<DmThuTucGiayPhep>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -8125304844112903959L;

			@Override
			public Predicate toPredicate(Root<DmThuTucGiayPhep> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();

				if (search != null && !search.isEmpty()) {
					Predicate ten = cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase() + "%");
					Predicate ma = cb.like(cb.lower(root.<String>get("ma")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(ten, ma));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
