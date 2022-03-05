package vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.chomeo.thoigiantiemphong.dao.model.ThoiGianTiemPhong;

public class ThoiGianTiemPhongSpecifications {
	public static Specification<ThoiGianTiemPhong> quickSearch( final String diaDiem, final Long phuongXaId, final Long quanHuyenId, final Long keHoachTiemPhongId,
			final LocalDateTime thoiGianTiemTuNgay,final LocalDateTime thoiGianTiemDenNgay) {
		return new Specification<ThoiGianTiemPhong>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<ThoiGianTiemPhong> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (phuongXaId != null) {
					predicates.add(cb.equal(root.<Long>get("phuongXaId"), phuongXaId));
				}
				
				if (quanHuyenId != null) {
					predicates.add(cb.equal(root.<Long>get("quanHuyenId"), quanHuyenId));
				}
				if (keHoachTiemPhongId !=null ) {
					predicates.add(cb.equal(root.<Long>get("keHoachTiemPhongId"), keHoachTiemPhongId));
				}
				if (diaDiem != null && !diaDiem.isEmpty()) {
					predicates.add(
					cb.like(cb.lower(root.<String>get("diaDiem")), "%" + diaDiem.toLowerCase().trim() + "%"));
				}
				if (thoiGianTiemTuNgay != null) {
					predicates.add(cb.equal(root.get("thoiGianTiem").as(LocalDate.class), thoiGianTiemTuNgay));
				}
				if (thoiGianTiemDenNgay != null) {
					predicates.add(cb.equal(root.get("thoiGianTiem").as(LocalDate.class), thoiGianTiemDenNgay));
				}
				
//				if (thoiGianTiemTuNgay != null) {
//					predicates.add(cb.greaterThanOrEqualTo(root.get("thoiGianTiemTuNgay").as(LocalDate.class), thoiGianTiemTuNgay));
//				}
//				if (thoiGianTiemDenNgay != null) {
//					predicates.add(cb.lessThanOrEqualTo(root.get("thoiGianTiemDenNgay").as(LocalDate.class), thoiGianTiemDenNgay));
//				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				
				return null;
			};
		};
	}
}
