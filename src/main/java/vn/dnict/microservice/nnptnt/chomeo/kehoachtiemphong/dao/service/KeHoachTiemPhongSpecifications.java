package vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.model.KeHoachTiemPhong;

public class KeHoachTiemPhongSpecifications {
	public static Specification<KeHoachTiemPhong> quickSearch(final String soKeHoach,
			 final LocalDate ngayDuKienTuNgay, final LocalDate ngayDuKienDenNgay,final LocalDate ngayBanHanhTuNgay,final LocalDate ngayBanHanhDenNgay,
			final String tenKeHoach) {
		return new Specification<KeHoachTiemPhong>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<KeHoachTiemPhong> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				query.distinct(true);
				if (tenKeHoach != null && !tenKeHoach.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.<String>get("tenKeHoach")), "%" + tenKeHoach.toLowerCase() + "%"));

				}
				if (soKeHoach != null && !soKeHoach.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.<String>get("soKeHoach")), "%" + soKeHoach.toLowerCase() + "%"));

				}
				
				if (ngayBanHanhTuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("ngayBanHanh").as(LocalDate.class),ngayBanHanhTuNgay));
				}
				if (ngayBanHanhDenNgay != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("ngayBanHanh").as(LocalDate.class), ngayBanHanhDenNgay));
				}
				if (ngayDuKienTuNgay != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("ngayDuKienTuNgay").as(LocalDate.class),ngayDuKienTuNgay));
				}
				if (ngayDuKienDenNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("ngayDuKienDenNgay").as(LocalDate.class), ngayDuKienDenNgay));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}

}
