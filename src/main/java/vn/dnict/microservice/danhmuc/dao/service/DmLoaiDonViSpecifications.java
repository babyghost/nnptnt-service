package vn.dnict.microservice.danhmuc.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.danhmuc.dao.model.DmLoaiDonVi;

public class DmLoaiDonViSpecifications {
	public static Specification<DmLoaiDonVi> findByDaXoaAndSearch(final Integer daXoa, final String search) {
		return new Specification<DmLoaiDonVi>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 4278694754574388871L;

			@Override
			public Predicate toPredicate(Root<DmLoaiDonVi> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

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
	
	public static Specification<DmLoaiDonVi> quickSearch(final String search, final Boolean trangThai) {
		return new Specification<DmLoaiDonVi>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -9132926358575981571L;

			@Override
			public Predicate toPredicate(Root<DmLoaiDonVi> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

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
