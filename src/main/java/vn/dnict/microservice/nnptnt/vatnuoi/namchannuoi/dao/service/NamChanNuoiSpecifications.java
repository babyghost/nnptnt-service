package vn.dnict.microservice.nnptnt.vatnuoi.namchannuoi.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.vatnuoi.namchannuoi.dao.model.NamChanNuoi;

public class NamChanNuoiSpecifications {

	public static Specification<NamChanNuoi> quickSearch(final String search, final Integer Quy) {
		return new Specification<NamChanNuoi>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<NamChanNuoi> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
//				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (search != null && !search.isEmpty()) {
					Predicate Nam = cb.like(cb.lower(root.<String>get("nam")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(Nam));
				}
				if (Quy != null) {
					predicates.add(cb.equal(root.<String>get("quy"), Quy));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
