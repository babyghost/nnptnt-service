package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;

public class NhiemVuNamSpecifications {
	public static Specification<NhiemVuNam> quickSearch(final Long keHoachNamId, final String tenNhiemVu, final LocalDate tuNgay,
			final LocalDate denNgay) {
		return new Specification<NhiemVuNam>() {
			private static final long serialVersionUID = -5902884843433373982L;
			@Override
			public Predicate toPredicate(Root<NhiemVuNam> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				query.distinct(true);
				
				if (keHoachNamId != null) {
					predicates.add(cb.equal(root.<Long>get("keHoachNamId"), keHoachNamId));
				}
				if (tenNhiemVu != null && !tenNhiemVu.isEmpty()) {
					predicates.add(cb.equal(root.<String>get("tenNhiemVu"), tenNhiemVu));
				}
				if (tuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("tuNgay").as(LocalDate.class),tuNgay));
				}
				if (denNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("denNgay").as(LocalDate.class),denNgay));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
		};
	}
}
