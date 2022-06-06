package vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.dao.model.KeHoachKhac;

public class KeHoachKhacSpecifications {
	public static Specification<KeHoachKhac> quichSearch(final String tenKeHoach, final Integer nam) {
		return new Specification<KeHoachKhac>() {

			/**
			 *
			 */
			private static final long serialVersionUID = 4914324802187118934L;

			@Override
			public Predicate toPredicate(Root<KeHoachKhac> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (tenKeHoach != null && !tenKeHoach.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenKeHoach")), "%" + tenKeHoach.toLowerCase().trim() + "%"));
				}

				if (nam != null && nam > -1) {
					predicates.add(cb.equal(root.<String>get("nam"), nam));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[]{}));
				}
				return null;
			}
		};
	}
}
