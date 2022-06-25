package vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.model.KeHoachNam;

public class KeHoachNamSpecifications {
	public static Specification<KeHoachNam> quichSearch(final Long donViChuTriId, final Integer nam,
			final String tenKeHoach, final String soKyHieu, final Boolean trangThai, final LocalDate tuNgayBanHanh,
			final LocalDate denNgayBanHanh) {
		return new Specification<KeHoachNam>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6206569043101389148L;

			@Override
			public Predicate toPredicate(Root<KeHoachNam> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (tenKeHoach != null && !tenKeHoach.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenKeHoach")),
							"%" + tenKeHoach.toLowerCase().trim() + "%"));
				}
				if (soKyHieu != null && !soKyHieu.isEmpty()) {
					predicates.add(
							cb.like(cb.lower(root.<String>get("soKyHieu")), "%" + soKyHieu.toLowerCase().trim() + "%"));
				}
				if (donViChuTriId != null && donViChuTriId > -1) {
					predicates.add(cb.equal(root.<String>get("donViChuTriId"), donViChuTriId));
				}
				if (nam != null && nam > -1) {
					predicates.add(cb.equal(root.<String>get("nam"), nam));
				}
				if (tuNgayBanHanh != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("ngayBanHanh").as(LocalDate.class), tuNgayBanHanh));
				}
				if (denNgayBanHanh != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("ngayBanHanh").as(LocalDate.class), denNgayBanHanh));
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
