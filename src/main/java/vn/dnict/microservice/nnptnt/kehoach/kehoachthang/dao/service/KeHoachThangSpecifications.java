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
	public static Specification<KeHoachThang> quickSearch(final Long donViChuTriId, final LocalDate thang,
			final String tenNhiemVu, final Long canBoThucHienId, final LocalDate thoiHanTuNgay, LocalDate thoiHanDenNgay,
			final Integer tinhTrang) {
		return new Specification<KeHoachThang>() {
			private static final long serialVersionUID = -5902884843433373982L;
			
			@Override
			public Predicate toPredicate(Root<KeHoachThang> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				query.distinct(true);
				
				if(donViChuTriId != null) {
					predicates.add(cb.equal(root.<Long>get("donViChuTriId"), donViChuTriId));
				}
				if (thang != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("thang").as(LocalDate.class), thang));
				}
				if(tenNhiemVu !=null && !tenNhiemVu.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.join("nhiemVuThang").<String>get("tenNhiemVu")), "%" + tenNhiemVu.toLowerCase() + "%"));
				}
				if(canBoThucHienId != null) {
					predicates.add(cb.equal(root.join("nhiemVuThang").<Long>get("canBoThucHienId"), canBoThucHienId));
				}
				if (thoiHanTuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.join("nhiemVuThang").get("thoiGian").as(LocalDate.class),thoiHanTuNgay));
				}
				if (thoiHanDenNgay != null) {
					predicates.add(cb.lessThanOrEqualTo(root.join("nhiemVuThang").get("thoiGian").as(LocalDate.class),thoiHanDenNgay));
				}
				if(tinhTrang != null) {
					predicates.add(cb.equal(root.join("nhiemVuThang").<Integer>get("tinhTrang"), tinhTrang));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[]{}));
				}
				return null;
			}
		};
	}
}
