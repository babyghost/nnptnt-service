package vn.dnict.microservice.danhmuc.dao.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.danhmuc.dao.model.DmCanBo;

public class DmCanBoSpecifications {
	public static Specification<DmCanBo> findByDaXoaAndSearch(final String search) {
		return new Specification<DmCanBo>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -7634833398033053829L;

			@Override
			public Predicate toPredicate(Root<DmCanBo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), 0));
				if (search != null && !search.isEmpty()) {
					Predicate hoTen = cb.like(cb.lower(root.<String>get("hoTen")), "%" + search.toLowerCase() + "%");
					Predicate chucVu = cb.like(cb.lower(root.<String>get("chucVu")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(hoTen, chucVu));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}

	public static Specification<DmCanBo> findByDaXoaAndSearchAndAppCode(final String appCode, final String search) {
		return new Specification<DmCanBo>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 141351032508257924L;

			@Override
			public Predicate toPredicate(Root<DmCanBo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), 0));
				if (appCode != null && !appCode.isEmpty()) {
					Predicate appCode1 = cb.equal(root.<String>get("appCode"), appCode);
					Predicate appCode2 = cb.equal(root.<String>get("appCode"), "DEFAULT");
					predicates.add(cb.or(appCode1, appCode2));
				}
				if (search != null && !search.isEmpty()) {
					Predicate hoTen = cb.like(cb.lower(root.<String>get("hoTen")), "%" + search.toLowerCase() + "%");
					Predicate chucVu = cb.like(cb.lower(root.<String>get("chucVu")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(hoTen, chucVu));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}

	public static Specification<DmCanBo> quickSearch(final String search, final String hoTen,
			final Long phongBanId, final Long donViId, final Long chucVuId, final String email, final String diaChi,
			final String cmndSo, final Long gioiTinhId, final LocalDate tuNgaySinh, final LocalDate denNgaySinh, final Long donViChaId) {
		return new Specification<DmCanBo>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -4372429915946751088L;

			@Override
			public Predicate toPredicate(Root<DmCanBo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), 0));
				if (search != null && !search.isEmpty()) {
					Predicate hoTen = cb.like(cb.lower(root.<String>get("hoTen")), "%" + search.toLowerCase() + "%");
					Predicate cmndSo = cb.like(cb.lower(root.<String>get("cmndSo")), "%" + search.toLowerCase() + "%");
					Predicate email = cb.like(cb.lower(root.<String>get("email")), "%" + search.toLowerCase() + "%");
					Predicate diaChi = cb.like(cb.lower(root.<String>get("diaChi")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(hoTen, cmndSo, email, diaChi));
				}
				if (hoTen != null && !hoTen.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("hoTen")), "%" + hoTen.toLowerCase() + "%"));
				}
				if (cmndSo != null && !cmndSo.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("cmndSo")), "%" + cmndSo.toLowerCase() + "%"));
				}
				if (email != null && !email.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("email")), "%" + email.toLowerCase() + "%"));
				}
				if (diaChi != null && !diaChi.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("diaChi")), "%" + diaChi.toLowerCase() + "%"));
				}
				if (phongBanId != null && phongBanId > 0) {
					predicates.add(cb.equal(root.<String>get("phongBan").get("id"), phongBanId));
				}
				if (donViId != null && donViId > 0) {
					predicates.add(cb.equal(root.<String>get("donVi").get("id"), donViId));
				}
				if (donViChaId != null && donViChaId > 0) {
					Predicate pDonViChaId = cb.equal(root.<String>get("donVi").get("donViChaId"), donViChaId);
					Predicate pDonViId = cb.equal(root.<String>get("donVi").get("id"), donViChaId);
					predicates.add(cb.or(pDonViChaId, pDonViId));
				}
				if (donViId != null && donViId > 0) {
					predicates.add(cb.equal(root.<String>get("donVi").get("id"), donViId));
				}
				if (chucVuId != null && chucVuId > 0) {
					predicates.add(cb.equal(root.<String>get("chucVu").get("id"), chucVuId));
				}
				if (gioiTinhId != null && gioiTinhId > 0) {
					predicates.add(cb.equal(root.<String>get("gioiTinh").get("id"), gioiTinhId));
				}
				if (tuNgaySinh != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("ngaySinh").as(LocalDate.class), tuNgaySinh));
				}
				if (denNgaySinh != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("ngaySinh").as(LocalDate.class), denNgaySinh));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
		};
	}
}
