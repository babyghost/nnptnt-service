package vn.dnict.microservice.core.dao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.core.dao.model.CoreNhomChucNang;

public class CoreNhomChucNangSpecifications {
	public static Specification<CoreNhomChucNang> quickSearch(final String search, final Boolean trangThai,
			final Long id, final Long nhomChucNangChaId) {

		return new Specification<CoreNhomChucNang>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -1922932126294073072L;

			@Override
			public Predicate toPredicate(Root<CoreNhomChucNang> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				//predicates.add(cb.isNull(root.<String>get("nhomChucNangChaId")));
				query.orderBy(cb.asc(root.<String>get("sapXep")));
				if (Objects.nonNull(search) && !search.isEmpty()) {
					Predicate ten = cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase().trim() + "%");
					Predicate ma = cb.like(cb.lower(root.<String>get("ma")), "%" + search.toLowerCase().trim() + "%");
					predicates.add(cb.or(ten, ma));
				}
				if (id != null && id > -1) {
					predicates.add(cb.notEqual(root.<String>get("id"), id));
				}
				if (nhomChucNangChaId != null) {
					if (nhomChucNangChaId > -1) {
						Predicate pChaId = cb.equal(root.<String>get("nhomChucNangChaId"), nhomChucNangChaId);
						Predicate pId = cb.equal(root.<String>get("id"), nhomChucNangChaId);
						predicates.add(cb.or(pChaId, pId));
					} else if (nhomChucNangChaId == -1) {
						predicates.add(cb.isNull(root.<String>get("nhomChucNangChaId")));
					}
				}
				if (Objects.nonNull(trangThai)) {
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
