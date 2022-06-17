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
	public static Specification<NhiemVuThang> quickSearch(final Long donViChuTriId, final List<LocalDate> thangs, final String tenNhiemVu,
			final Long canBoThucHienId, final LocalDate thoiHanTuNgay, final LocalDate thoiHanDenNgay, final Integer tinhTrang) {
		return new Specification<NhiemVuThang>() {
			private static final long serialVersionUID = -5902884843433373982L;
			
			@Override
			public Predicate toPredicate(Root<NhiemVuThang> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				query.distinct(true);
				
				if(donViChuTriId != null) {
					predicates.add(cb.equal(root.join("keHoachThang").<Long>get("donViChuTriId"), donViChuTriId));
				}
				if (thangs != null && !thangs.isEmpty()) {
					Expression<List<LocalDate>> valuethang = cb.literal(thangs);
					Expression<String> expression = root.join("keHoachThang").get("thang");
					Predicate inList = expression.in(valuethang);
					predicates.add(inList);
				}
				if(tenNhiemVu !=null && !tenNhiemVu.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenNhiemVu")), "%" + tenNhiemVu.toLowerCase() + "%"));
				}
				if(canBoThucHienId != null) {
					predicates.add(cb.equal(root.<Long>get("canBoThucHienId"), canBoThucHienId));
				}
				if (thoiHanTuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("thoiGian").as(LocalDate.class),thoiHanTuNgay));
				}
				if (thoiHanDenNgay != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("thoiGian").as(LocalDate.class),thoiHanDenNgay));
				}
				if(tinhTrang != null) {
					predicates.add(cb.equal(root.<Integer>get("tinhTrang"), tinhTrang));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[]{}));
				}
				return null;
			}
		};
	}
}
