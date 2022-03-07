package vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.service;

import java.util.ArrayList; 
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.model.CoSoChanNuoi;

public class CoSoChanNuoiSpecifications {
	public static Specification<CoSoChanNuoi> quickSearch(final String search, final Long phuongXaId, Long quanHuyenId) {
		return new Specification<CoSoChanNuoi>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<CoSoChanNuoi> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();

				if (search != null && !search.isEmpty()) {
					Predicate tencoso = cb.like(cb.lower(root.<String>get("tenCoSo")), "%" + search.toLowerCase() + "%");
					Predicate tenchucoso = cb.like(cb.lower(root.<String>get("tenChuCoSo")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(tencoso, tenchucoso));
				}
				if (phuongXaId != null) {
					predicates.add(cb.equal(root.<Long>get("phuongXaId"), phuongXaId));
				}
				
				if (quanHuyenId != null) {
					predicates.add(cb.equal(root.<Long>get("quanHuyenId"), quanHuyenId));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}