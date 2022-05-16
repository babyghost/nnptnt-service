package vn.dnict.microservice.nnptnt.hopdong.danhmuc.phuongthucbaolanhhd.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.hopdong.danhmuc.phuongthucbaolanhhd.dao.model.DmPhuongThucBaoLanhHd;

public class DmPhuongThucBaoLanhHdSpecifications {
	public static Specification<DmPhuongThucBaoLanhHd> findByDaXoaAndSearch(final String search,
			final Integer type) {
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
				if (search != null && !search.isEmpty()) {
					Predicate ten = cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase() + "%");
					Predicate ma = cb.like(cb.lower(root.<String>get("ma")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(ten, ma));
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

	public static Specification<DmPhuongThucBaoLanhHd> quickSearch(final String search, final Integer type,
			final Boolean trangThai) {
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
				if (search != null && !search.isEmpty()) {
					Predicate ten = cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase() + "%");
					Predicate ma = cb.like(cb.lower(root.<String>get("ma")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(ten, ma));
				}
				if (type != null && type > 0) {
					predicates.add(cb.equal(root.<String>get("type"), type));
				}
				if (trangThai != null) {
					predicates.add(cb.equal(root.<String>get("trangThai"), trangThai));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
