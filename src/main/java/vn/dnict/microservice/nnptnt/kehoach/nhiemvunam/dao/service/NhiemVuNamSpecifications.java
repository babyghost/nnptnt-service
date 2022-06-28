package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;
import vn.dnict.microservice.utils.Constants;

public class NhiemVuNamSpecifications {
	public static Specification<NhiemVuNam> quichSearch(final Long donViChuTriId, final List<Integer> tinhTrangs, final Integer nam,
			final Long keHoachNamId, final LocalDate tuNgay, final LocalDate denNgay, final String tenNhiemVu) {
		return new Specification<NhiemVuNam>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6206569043101389148L;

			@Override
			public Predicate toPredicate(Root<NhiemVuNam> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				query.distinct(true);
				
				if (donViChuTriId != null && donViChuTriId > -1) {
					predicates.add(cb.equal(root.get("keHoachNam").get("donViChuTriId"), donViChuTriId));
				}
				if (tinhTrangs != null && !tinhTrangs.isEmpty()) {
					Predicate nullTinhTrang = null;
					if (tinhTrangs.contains(Constants.QLKH_TINHTRANG_CHUATHUCHIEN)) {
						Expression<List<TienDoNhiemVuNam>> tienDos = root.get("tienDoNhiemVuNams");
						Expression<Integer> size = cb.size(tienDos);
						nullTinhTrang = cb.equal(size, 0);
						Expression<List<Integer>> valuetinhTrangs = cb.literal(tinhTrangs);
						Expression<String> expression = root.join("tienDoNhiemVuNams", JoinType.LEFT)
								.get("tinhTrang");
						Predicate inList = expression.in(valuetinhTrangs);
						predicates.add(cb.or(inList, nullTinhTrang));
					} else {
						Expression<List<Integer>> valuetinhTrangs = cb.literal(tinhTrangs);
						Expression<String> expression = root.join("tienDoNhiemVuNams").get("tinhTrang");
						Predicate inList = expression.in(valuetinhTrangs);
						predicates.add(inList);
					}
				}
				if (nam != null && nam > -1) {
					predicates.add(cb.equal(root.get("keHoachNam").get("nam"), nam));
				}
				if (keHoachNamId != null && keHoachNamId > -1) {
					predicates.add(cb.equal(root.<String>get("keHoachNamId"), keHoachNamId));
				}
				if (tuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("tuNgay").as(LocalDate.class), tuNgay));
				}
				if (denNgay != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("denNgay").as(LocalDate.class), denNgay));
				}
				if (tenNhiemVu != null && !tenNhiemVu.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenNhiemVu")),
							"%" + tenNhiemVu.toLowerCase().trim() + "%"));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
	
	public static Specification<NhiemVuNam> thongke(final Long donViChuTriId, final Integer nam, final Long keHoachNamId, 
			final List<Integer> tinhTrangs, final LocalDate tuNgay, final LocalDate denNgay, final String tenNhiemVu) {
		return new Specification<NhiemVuNam>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6206569043101389148L;

			@Override
			public Predicate toPredicate(Root<NhiemVuNam> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				query.distinct(true);
				
				if (donViChuTriId != null && donViChuTriId > -1) {
					predicates.add(cb.equal(root.get("keHoachNam").get("donViChuTriId"), donViChuTriId));
				}
				if (nam != null && nam > -1) {
					predicates.add(cb.equal(root.get("keHoachNam").get("nam"), nam));
				}
				if (keHoachNamId != null && keHoachNamId > -1) {
					predicates.add(cb.equal(root.<String>get("keHoachNamId"), keHoachNamId));
				}
				if (tinhTrangs != null && !tinhTrangs.isEmpty()) {
					Predicate nullTinhTrang = null;
					if (tinhTrangs.contains(Constants.QLKH_TINHTRANG_CHUATHUCHIEN)) {
						Expression<List<TienDoNhiemVuNam>> tienDos = root.get("tienDoNhiemVuNams");
						Expression<Integer> size = cb.size(tienDos);
						nullTinhTrang = cb.equal(size, 0);
						Expression<List<Integer>> valuetinhTrangs = cb.literal(tinhTrangs);
						Expression<String> expression = root.join("tienDoNhiemVuNams", JoinType.LEFT)
								.get("tinhTrang");
						Predicate inList = expression.in(valuetinhTrangs);
						predicates.add(cb.or(inList, nullTinhTrang));
					} else {
						Expression<List<Integer>> valuetinhTrangs = cb.literal(tinhTrangs);
						Expression<String> expression = root.join("tienDoNhiemVuNams").get("tinhTrang");
						Predicate inList = expression.in(valuetinhTrangs);
						predicates.add(inList);
					}
				}
				if (tuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("tuNgay").as(LocalDate.class), tuNgay));
				}
				if (denNgay != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("denNgay").as(LocalDate.class), denNgay));
				}
				if (tenNhiemVu != null && !tenNhiemVu.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenNhiemVu")),
							"%" + tenNhiemVu.toLowerCase().trim() + "%"));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
