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
	public static Specification<NhiemVuNam> quickSearch( final Long donViChuTriId,final Long keHoachNamId, final Integer nam, final Boolean tinhTrang,
			final String tenNhiemVu, final LocalDate tuNgay, final LocalDate denNgay) {
		return new Specification<NhiemVuNam>() {
			private static final long serialVersionUID = -5902884843433373982L;
			@Override
			public Predicate toPredicate(Root<NhiemVuNam> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				query.distinct(true);
				
				if(donViChuTriId != null && donViChuTriId > -1) {
					predicates.add(cb.equal(root.join("keHoachNam").<Long>get("donViChuTriId"), donViChuTriId));
				
				}
				
				if (nam != null) {
					predicates.add(cb.equal(root.join("keHoachNam").get("nam"), nam));
				}
				if (keHoachNamId != null && keHoachNamId > -1) {
					predicates.add(cb.equal(root.join("keHoachNam").<Long>get("id"), keHoachNamId));
				}
				if (tinhTrang != null) {
					predicates.add(cb.equal(root.join("tienDoNhiemVuNam").get("tinhTrang"), tinhTrang));
				}
				if (tenNhiemVu != null && !tenNhiemVu.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenNhiemVu")), "%" + tenNhiemVu.toLowerCase() + "%"));
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
	
	public static Specification<NhiemVuNam> tongHopKeHoachNam( final Long donViChuTriId, final Long keHoachNamId, final Integer nam, final Boolean tinhTrang,
			final String tenNhiemVu, final LocalDate tuNgay, final LocalDate denNgay) {
		return new Specification<NhiemVuNam>() {
			private static final long serialVersionUID = -5902884843433373982L;
			@Override
			public Predicate toPredicate(Root<NhiemVuNam> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				query.distinct(true);
				
				if(donViChuTriId != null && donViChuTriId > -1) {
					predicates.add(cb.equal(root.join("keHoachNam").get("donViChuTriId"), donViChuTriId));
				}
				if (nam != null) {
					predicates.add(cb.equal(root.join("keHoachNam").get("nam"), nam));
				}
				if (keHoachNamId != null && keHoachNamId > -1) {
					predicates.add(cb.equal(root.<Long>get("keHoachNamId"), keHoachNamId));
				}
				if (tinhTrang != null) {
					predicates.add(cb.equal(root.join("tienDoNhiemVuNam").get("tinhTrang"), tinhTrang));
				}
				if (tenNhiemVu != null && !tenNhiemVu.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenNhiemVu")), "%" + tenNhiemVu.toLowerCase() + "%"));
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
