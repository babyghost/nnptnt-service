package vn.dnict.microservice.uaa.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.uaa.dao.model.User;

class UserSpecifications {
	public static Specification<User> quickSearch(final String email, final String userName, final String role,
			final Boolean trangThai, final Boolean isDungChung, final String appCode) {
		return new Specification<User>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -2375071718527934937L;

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (email != null && !email.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.<String>get("email")), "%" + email.toLowerCase().trim() + "%"));
				}
				if (userName != null && !userName.isEmpty()) {
					predicates.add(
							cb.like(cb.lower(root.<String>get("userName")), "%" + userName.toLowerCase().trim() + "%"));
				}
				if (role != null && !role.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("roles").get("role")),
							"%" + role.toLowerCase().trim() + "%"));
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
