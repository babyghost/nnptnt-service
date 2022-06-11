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
	public static Specification<KeHoachNam> quickSearch(final Integer nam, final String tenKeHoach, final Boolean trangThai,
			final String soKyHieu, final LocalDate ngayBanHanhTuNgay, final LocalDate ngayBanHanhDenNgay) {
		return new Specification<KeHoachNam> () {
			private static final long serialVersionUID = -5902884843433373982L;
			
			@Override
			public Predicate toPredicate(Root<KeHoachNam> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				query.distinct(true);
				if(nam != null) {
					predicates.add(cb.equal(root.<Integer>get("nam"), nam));
				}
				if(tenKeHoach !=null && !tenKeHoach.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenKeHoach")), "%" + tenKeHoach.toLowerCase() + "%"));
				}
				if(trangThai != null) {
					predicates.add(cb.equal(root.<String>get("trangThai"), trangThai));
				}
				if(soKyHieu != null && soKyHieu.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("soKyHieu")), "%" + soKyHieu.toLowerCase() + "%"));
				}
				if (ngayBanHanhTuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("ngayBanHanh").as(LocalDate.class),ngayBanHanhTuNgay));
				}
				if (ngayBanHanhDenNgay != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("ngayBanHanh").as(LocalDate.class), ngayBanHanhDenNgay));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[]{}));
				}
				return null;
			}
		};
	}

}
