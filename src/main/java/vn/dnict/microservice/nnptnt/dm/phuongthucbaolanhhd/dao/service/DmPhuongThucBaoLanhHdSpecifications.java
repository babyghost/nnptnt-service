package vn.dnict.microservice.nnptnt.dm.phuongthucbaolanhhd.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.dm.phuongthucbaolanhhd.dao.model.DmPhuongThucBaoLanhHd;

public class DmPhuongThucBaoLanhHdSpecifications {
	public static Specification<DmPhuongThucBaoLanhHd> findByDaXoaAndSearch(final String ten, final String ma, final Integer type) {
		return new Specification<DmPhuongThucBaoLanhHd>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8076752724560368356L;

			@Override
			public Predicate toPredicate(Root<DmPhuongThucBaoLanhHd> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (ten != null && !ten.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("ten")), "%" + ten.toLowerCase() + "%"));
				}
				if (ma != null && !ma.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("ma")), "%" + ma.toLowerCase() + "%"));
				}
				if (type != null && type > 0) {
					predicates.add(cb.equal(root.<String>get("type"), type));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}

	public static Specification<DmPhuongThucBaoLanhHd> quickSearch(final String ten, final String ma, final Boolean trangThai,
			final Integer type) {
		return new Specification<DmPhuongThucBaoLanhHd>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -8275016414408494612L;

			@Override
			public Predicate toPredicate(Root<DmPhuongThucBaoLanhHd> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (ten != null && !ten.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("ten")), "%" + ten.toLowerCase() + "%"));
				}
				if (ma != null && !ma.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("ma")), "%" + ma.toLowerCase() + "%"));
				}
				if (trangThai != null) {
					predicates.add(cb.equal(root.<String>get("trangThai"), trangThai));
				}
				if (type != null && type > 0) {
					predicates.add(cb.equal(root.<String>get("type"), type));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
