package vn.dnict.microservice.nnptnt.qlchomeo.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.ThongTinChoMeo;

public class ThongTinChoMeoSpecifications {
	public static Specification<ThongTinChoMeo> quickSearch(final String search, final Integer trangThai) {
		return new Specification<ThongTinChoMeo>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<ThongTinChoMeo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), true));
				if (search != null && !search.isEmpty()) {
					Predicate tenConVat = cb.like(cb.lower(root.<String>get("tenConVat")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(tenConVat));
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
