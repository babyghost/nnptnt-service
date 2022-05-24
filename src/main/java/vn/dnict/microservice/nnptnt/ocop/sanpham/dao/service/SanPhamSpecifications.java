package vn.dnict.microservice.nnptnt.ocop.sanpham.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.ocop.sanpham.dao.model.SanPham;

public class SanPhamSpecifications {
	public static Specification<SanPham> quickSearch(final String ten, final String chuThe, final Long nganhHangId,
			final Long phanNhomId, final Long phanHangId, final Integer trangThai, final String quyetDinh,
			final Long nhomId) {
		return new Specification<SanPham>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<SanPham> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (ten != null && !ten.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("ten")), "%" + ten.toLowerCase() + "%"));

				}
				if (chuThe != null && !chuThe.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.join("doanhNghiep").<String>get("chuThe")), "%" + chuThe.toLowerCase() + "%"));

				}
				if (quyetDinh != null && !quyetDinh.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.<String>get("quyetDinh")), "%" + quyetDinh.toLowerCase() + "%"));

				}
				if (trangThai != null) {
					predicates.add(cb.equal(root.<String>get("trangThai"), trangThai));
				}
				if (nganhHangId != null) {
					predicates.add(cb.equal(root.<String>get("nganhHangId"), nganhHangId));
				}
				if (phanNhomId != null) {
					predicates.add(cb.equal(root.<String>get("phanNhomId"), phanNhomId));
				}
				if (phanHangId != null) {
					predicates.add(cb.equal(root.<String>get("phanHangId"), phanHangId));
				}
				if (nhomId != null) {
					predicates.add(cb.equal(root.<String>get("nhomId"), nhomId));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
