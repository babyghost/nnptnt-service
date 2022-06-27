package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.model.NhiemVuThang;

public class NhiemVuThangSpecifications {
	public static Specification<NhiemVuThang> quichSearch(final Long donViChuTriId, final List<LocalDate> thangs, final Integer tinhTrang,
			final String tenNhiemVu, final LocalDate tuNgay, final LocalDate denNgay) {
		return new Specification<NhiemVuThang>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6206569043101389148L;

			@Override
			public Predicate toPredicate(Root<NhiemVuThang> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				
				if(donViChuTriId != null && donViChuTriId > -1) {
					predicates.add(cb.equal(root.join("keHoachThang").<Long>get("donViChuTriId"), donViChuTriId));
				}
				if (thangs != null && !thangs.isEmpty()) {
					List<Integer> years = new ArrayList<Integer>();
					List<Integer> monThs = new ArrayList<Integer>();
					for (LocalDate localDate : thangs) {
						years.add(localDate.getYear());
						monThs.add(localDate.getMonthValue());
					}
					Expression<List<Integer>> valueMonths = cb.literal(monThs);
					Expression<List<Integer>> valueYears = cb.literal(years);
					Expression<Integer> year = cb.function("YEAR", Integer.class, root.get("thang"));
					Expression<Integer> month = cb.function("MONTH", Integer.class, root.get("thang"));
					Predicate inYear = year.in(valueYears);
					Predicate inMonth = month.in(valueMonths);
					predicates.add(cb.and(inYear, inMonth));
				}
				if(tinhTrang != null) {
					predicates.add(cb.equal(root.<Integer>get("tinhTrang"), tinhTrang));
				}
				if (tenNhiemVu != null && !tenNhiemVu.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenNhiemVu")), "%" + tenNhiemVu.toLowerCase().trim() + "%"));
				}
				if (tuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("thoiGian").as(LocalDate.class), tuNgay));
				}
				if (denNgay != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("thoiGian").as(LocalDate.class), denNgay));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
	
	public static Specification<NhiemVuThang> thongKeThang(final Long donViChuTriId, final List<LocalDate > thangs,
			final String tenNhiemVu,final List<Integer> tinhTrangs, final Long canBoThucHienId, final LocalDate tuNgay,
			LocalDate denNgay) {
		return new Specification<NhiemVuThang>() {
			private static final long serialVersionUID = -5902884843433373982L;
			
			@Override
			public Predicate toPredicate(Root<NhiemVuThang> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				query.distinct(true);
				
				if (donViChuTriId != null && donViChuTriId > -1) {
					predicates.add(cb.equal(root.get("keHoachThang").get("donViChuTriId"), donViChuTriId));
				}
				if (thangs != null && !thangs.isEmpty()) {
					List<Integer> years = new ArrayList<Integer>();
					List<Integer> monThs = new ArrayList<Integer>();
					for (LocalDate localDate : thangs) {
						years.add(localDate.getYear());
						monThs.add(localDate.getMonthValue());
					}
					Expression<List<Integer>> valueMonths = cb.literal(monThs);
					Expression<List<Integer>> valueYears = cb.literal(years);
					Expression<Integer> year = cb.function("YEAR", Integer.class, root.get("thang"));
					Expression<Integer> month = cb.function("MONTH", Integer.class, root.get("thang"));
					Predicate inYear = year.in(valueYears);
					Predicate inMonth = month.in(valueMonths);
					predicates.add(cb.and(inYear, inMonth));
				}
				if (tenNhiemVu != null && !tenNhiemVu.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenNhiemVu")), "%" + tenNhiemVu.toLowerCase().trim() + "%"));
				}
				if (tinhTrangs != null && !tinhTrangs.isEmpty()) {
					Expression<List<Integer>> valuetinhTrangs = cb.literal(tinhTrangs);
					Expression<String> expression = root.get("tinhTrang");
					Predicate inList = expression.in(valuetinhTrangs);
					predicates.add(inList);
				}
				if(canBoThucHienId != null) {
					predicates.add(cb.equal(root.<Long>get("canBoThucHienId"), canBoThucHienId));
				}
				if (tuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("thoiGian").as(LocalDate.class), tuNgay));
				}
				if (denNgay != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("thoiGian").as(LocalDate.class), denNgay));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[]{}));
				}
				return null;
			}
		};
		
	}
}
