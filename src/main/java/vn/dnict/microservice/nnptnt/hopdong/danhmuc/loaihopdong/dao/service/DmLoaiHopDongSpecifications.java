package vn.dnict.microservice.nnptnt.hopdong.danhmuc.loaihopdong.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.hopdong.danhmuc.loaihopdong.dao.model.DmLoaiHopDong;

public class DmLoaiHopDongSpecifications {

	public static Specification<DmLoaiHopDong> quickSearch(final String ten, final String ma, final Boolean trangThai) {
		return new Specification<DmLoaiHopDong>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1638920631817242981L;

			@Override
			public Predicate toPredicate(Root<DmLoaiHopDong> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<Predicate>();
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
				if (predicates.size() > 0) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
