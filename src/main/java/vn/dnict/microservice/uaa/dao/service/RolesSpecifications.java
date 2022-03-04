package vn.dnict.microservice.uaa.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.uaa.dao.model.Roles;


class RolesSpecifications {
	public static Specification<Roles> quickSearch(final String roleName) {
		return new Specification<Roles>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 8841644516650894137L;

			@Override
			public Predicate toPredicate(Root<Roles> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				if (roleName != null && !roleName.isEmpty()) {
					predicates.add(
							cb.like(cb.lower(root.<String>get("roleName")), "%" + roleName.toLowerCase().trim() + "%"));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
