package vn.dnict.microservice.nnptnt.dm.linhvuc.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.model.DmLinhVuc;

public class DmLinhVucSpecifications {
	public static Specification<DmLinhVuc> quickSearch(final String search, final Boolean trangThai) {
		return new Specification<DmLinhVuc>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<DmLinhVuc> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (search != null && !search.isEmpty()) {
					Predicate ten = cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase() + "%");

					predicates.add(cb.or(ten));
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
