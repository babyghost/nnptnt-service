package vn.dnict.microservice.nnptnt.ocop.chuthe.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.ocop.chuthe.dao.model.DoanhNghiep;

public class DoanhNghiepSpecifications {
	public static Specification<DoanhNghiep> quickSearch(final String ten,final String chuSoHuu,final Long loaiDoanhNghiepId,
			final	Long loaiHinhId, final Long nganhNgheId , final Integer trangThai) {
		return new Specification<DoanhNghiep>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<DoanhNghiep> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (ten != null && !ten.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("ten")), "%" + ten.toLowerCase() + "%"));
					

				}
				if (chuSoHuu != null && !chuSoHuu.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("chuSoHuu")), "%" + chuSoHuu.toLowerCase() + "%"));
					

				}
				if (trangThai != null) {
					predicates.add(cb.equal(root.<String>get("trangThai"), trangThai));
				}
				if (nganhNgheId != null) {
					predicates.add(cb.equal(root.<String>get("nganhNgheId"), nganhNgheId));
				}
				if (loaiDoanhNghiepId != null) {
					predicates.add(cb.equal(root.<String>get("loaiDoanhNghiepId"), loaiDoanhNghiepId));
				}
				if (loaiHinhId != null) {
					predicates.add(cb.equal(root.<String>get("loaiHinhId"), loaiHinhId));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
