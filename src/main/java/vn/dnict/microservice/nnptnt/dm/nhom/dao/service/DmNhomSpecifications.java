package vn.dnict.microservice.nnptnt.dm.nhom.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.dm.nhom.dao.model.DmNhom;

public class DmNhomSpecifications {
	public static Specification<DmNhom> quickSearch(final String ten,final Long dmNganhHangId ,final Boolean trangThai) {
		return new Specification<DmNhom>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<DmNhom> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (ten != null && !ten.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("ten")), "%" + ten.toLowerCase() + "%"));
					
				}
				if (dmNganhHangId != null) {
					predicates.add(cb.equal(root.<String>get("dmNganhHangId"), dmNganhHangId));
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
