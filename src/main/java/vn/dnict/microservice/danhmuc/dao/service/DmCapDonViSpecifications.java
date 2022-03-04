package vn.dnict.microservice.danhmuc.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.danhmuc.dao.model.DmCapDonVi;

public class DmCapDonViSpecifications {
	public static Specification<DmCapDonVi> findByDaXoaAndSearch(final Integer daXoa, final String search) {
		return new Specification<DmCapDonVi>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3628834397037938981L;

			@Override
			public Predicate toPredicate(Root<DmCapDonVi> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), daXoa));
				if (search != null && !search.isEmpty()) {
					Predicate ten = cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase() + "%");
					predicates.add(ten);
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}

	public static Specification<DmCapDonVi> quickSearch(final String search) {
		return new Specification<DmCapDonVi>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -1754332118866969495L;

			@Override
			public Predicate toPredicate(Root<DmCapDonVi> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), 0));
				if (search != null && !search.isEmpty()) {
					Predicate ten = cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase() + "%");
					Predicate ma = cb.like(cb.lower(root.<String>get("ma")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(ten, ma));
				}
				/*if (appCode != null && !appCode.isEmpty()) {
					predicates.add(cb.equal(root.<String>get("appCode"), appCode));
				}*/
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
