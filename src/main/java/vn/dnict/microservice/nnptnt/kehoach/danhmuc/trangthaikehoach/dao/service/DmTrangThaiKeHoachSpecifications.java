package vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.dao.model.DmTrangThaiKeHoach;

public class DmTrangThaiKeHoachSpecifications {
	public static Specification<DmTrangThaiKeHoach> quichSearch(final String search) {
		return new Specification<DmTrangThaiKeHoach>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -4867988808098131439L;
			
			@Override
			public Predicate toPredicate(Root<DmTrangThaiKeHoach> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>();
				if (search != null && !search.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase().trim() + "%"));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[]{}));
				}
				return null;
			}
			
		};
	}
}
