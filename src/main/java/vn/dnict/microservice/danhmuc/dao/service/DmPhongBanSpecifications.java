package vn.dnict.microservice.danhmuc.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.danhmuc.dao.model.DmPhongBan;

public class DmPhongBanSpecifications {
	public static Specification<DmPhongBan> findByDaXoaAndSearch(final Integer daXoa, final String search) {
		return new Specification<DmPhongBan>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -4898481109372931454L;

			@Override
			public Predicate toPredicate(Root<DmPhongBan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), daXoa));
				if (search != null && !search.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase() + "%"));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}

	public static Specification<DmPhongBan> quickSearch(final String search, final Long donViId,
			final Boolean trangThai) {
		return new Specification<DmPhongBan>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -8824097606467186206L;

			@Override
			public Predicate toPredicate(Root<DmPhongBan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), 0));
				if (search != null && !search.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase() + "%"));
				}
				if (trangThai != null) {
					predicates.add(cb.equal(root.<String>get("trangThai"), trangThai));
				}
				if (donViId != null && donViId > 0) {
					predicates.add(cb.equal(root.<String>get("donViId"), donViId));
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
