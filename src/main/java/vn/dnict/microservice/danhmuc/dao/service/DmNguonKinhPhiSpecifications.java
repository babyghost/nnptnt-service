package vn.dnict.microservice.danhmuc.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.danhmuc.dao.model.DmNguonKinhPhi;

public class DmNguonKinhPhiSpecifications {

	public static Specification<DmNguonKinhPhi> quickSearch(final String search, final Boolean trangThai, final Long id,
			final Long chaId) {
		return new Specification<DmNguonKinhPhi>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -9132926358575981571L;

			@Override
			public Predicate toPredicate(Root<DmNguonKinhPhi> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (search != null && !search.isEmpty()) {
					Predicate ten = cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase() + "%");
					Predicate ma = cb.like(cb.lower(root.<String>get("ma")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(ten, ma));
				}
				if (trangThai != null) {
					predicates.add(cb.equal(root.<String>get("trangThai"), trangThai));
				}
				if (id != null && id > -1) {
					predicates.add(cb.notEqual(root.<String>get("id"), id));
				}
				if (chaId != null) {
					if (chaId > -1) {
						Predicate pChaId = cb.equal(root.<String>get("chaId"), chaId);
						Predicate pId = cb.equal(root.<String>get("id"), chaId);
						predicates.add(cb.or(pChaId, pId));
					} else if (chaId == -1) {
						predicates.add(cb.isNull(root.<String>get("chaId")));
					}
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
