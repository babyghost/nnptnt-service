package vn.dnict.microservice.danhmuc.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;

public class DmDonViSpecifications {
	public static Specification<DmDonVi> findByDaXoaAndSearch(final Boolean daXoa, final String search) {
		return new Specification<DmDonVi>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3674738840779782416L;

			@Override
			public Predicate toPredicate(Root<DmDonVi> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<Boolean>get("daXoa"), daXoa));
				if (search != null && !search.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenDonVi")), "%" + search.toLowerCase() + "%"));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}

	public static Specification<DmDonVi> findByDaXoaAndTrangThaiAndSearch(final Boolean daXoa, final Integer trangThai,
			final String search) {
		return new Specification<DmDonVi>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3934201991245381703L;

			@Override
			public Predicate toPredicate(Root<DmDonVi> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<Boolean>get("daXoa"), daXoa));
				if (search != null && !search.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenDonVi")), "%" + search.toLowerCase() + "%"));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}

	public static Specification<DmDonVi> quickSearch(final String search, final String tenDonVi, final String diaChi,
			final String soDienThoai, final String email, final Long donViChaId, final Long capId,
			final Long loaiDonViId) {
		return new Specification<DmDonVi>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1056991365730478069L;

			@Override
			public Predicate toPredicate(Root<DmDonVi> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<Boolean>get("daXoa"), false));
				if (search != null && !search.isEmpty()) {
					Predicate tenDonVi = cb.like(cb.lower(root.<String>get("tenDonVi")),
							"%" + search.toLowerCase() + "%");
					Predicate diaChi = cb.like(cb.lower(root.<String>get("diaChi")), "%" + search.toLowerCase() + "%");
					Predicate soDienThoai = cb.like(cb.lower(root.<String>get("soDienThoai")),
							"%" + search.toLowerCase() + "%");
					Predicate email = cb.like(cb.lower(root.<String>get("email")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(tenDonVi, diaChi, soDienThoai, email));
				}

				if (tenDonVi != null && !tenDonVi.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenDonVi")), "%" + tenDonVi.toLowerCase() + "%"));
				}
				if (diaChi != null && !diaChi.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("diaChi")), "%" + diaChi.toLowerCase() + "%"));
				}
				if (soDienThoai != null && !soDienThoai.isEmpty()) {
					predicates.add(
							cb.like(cb.lower(root.<String>get("soDienThoai")), "%" + soDienThoai.toLowerCase() + "%"));
				}
				if (email != null && !email.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("email")), "%" + email.toLowerCase() + "%"));
				}
				if (donViChaId != null && donViChaId > 0) {
					Predicate pDonViChaId = cb.equal(root.<String>get("donViChaId"), donViChaId);
					Predicate pDonViId = cb.equal(root.<String>get("id"), donViChaId);
					predicates.add(cb.or(pDonViChaId, pDonViId));
				}
				if (capId != null && capId > 0) {
					predicates.add(cb.equal(root.<String>get("capId"), capId));
				}
				if (loaiDonViId != null && loaiDonViId > 0) {
					predicates.add(cb.equal(root.<String>get("loaiDonViId"), loaiDonViId));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
