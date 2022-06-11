package vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.model.DmLoaiNhiemVu;

public class DmLoaiNhiemVuSpecifications {
	public static Specification<DmLoaiNhiemVu> quichSearch(final String search, final Boolean trangThai) {
		return new Specification<DmLoaiNhiemVu>() {
			
			/**
			 *
			 */
			private static final long serialVersionUID = -5902884843433373982L;

			@Override
			public Predicate toPredicate(Root<DmLoaiNhiemVu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (search != null && !search.isEmpty()) {
					Predicate ten = cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase().trim() + "%");
					Predicate ma = cb.like(cb.lower(root.<String>get("ma")), "%" + search.toLowerCase().trim() + "%");
					predicates.add(cb.or(ten, ma));
				}
				
				if (trangThai != null) {
					predicates.add(cb.equal(root.<String>get("trangThai"), trangThai));
				}
				
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[]{}));
				}
				
				return null;
			}

		};
	}
}
