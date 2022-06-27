package vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.model.KeHoachThang;

public class KeHoachThangSpecifications {
	public static Specification<KeHoachThang> quichSearch(final Long donViChuTriId, final Integer thang,
			final String tenNhiemVu, final Long canBoThucHienId, final LocalDate tuThoiHan, final LocalDate denThoiHan,
			final Integer tinhTrang) {
		return new Specification<KeHoachThang>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 8414845006438913353L;

			@Override
			public Predicate toPredicate(Root<KeHoachThang> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));

				if (donViChuTriId != null && donViChuTriId > -1) {
					predicates.add(cb.equal(root.<String>get("donViChuTriId"), donViChuTriId));
				}
				if (thang != null && thang > -1) {
					predicates.add(cb.equal(root.<String>get("thang"), thang));
				}
				if (tenNhiemVu != null && !tenNhiemVu.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenNhiemVu")), "%" + tenNhiemVu.toLowerCase().trim() + "%"));
				}
				if (canBoThucHienId != null && canBoThucHienId > -1) {
					predicates.add(cb.equal(root.join("nhiemVuThangs").<String>get("canBoThucHienId"), canBoThucHienId));
				}
				if (tuThoiHan != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("thoiGian").as(LocalDate.class), tuThoiHan));
				}
				if (denThoiHan != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("thoiGian").as(LocalDate.class), denThoiHan));
				}
				if (tinhTrang != null && tinhTrang > -1) {
					predicates.add(cb.equal(root.<String>get("tinhTrang"), tinhTrang));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
