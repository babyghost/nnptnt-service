package vn.dnict.microservice.uaa.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.uaa.dao.model.UserLog;



class UserLogSpecifications {
	public static Specification<UserLog> quickSearch(final String email) {
		return new Specification<UserLog>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 3064764018098539769L;

			@Override
			public Predicate toPredicate(Root<UserLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				if (email != null && !email.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.<String>get("email")), "%" + email.toLowerCase().trim() + "%"));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
