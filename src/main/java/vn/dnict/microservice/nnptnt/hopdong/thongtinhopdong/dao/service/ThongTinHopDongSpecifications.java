package vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.model.ThongTinHopDong;


public class ThongTinHopDongSpecifications {
	public static Specification<ThongTinHopDong> findByDaXoaAndSearch(final String search) {
		return new Specification<ThongTinHopDong>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1754889514801162651L;

			@Override
			public Predicate toPredicate(Root<ThongTinHopDong> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (search != null && !search.isEmpty()) {
					Predicate ten = cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase() + "%");
					Predicate ma = cb.like(cb.lower(root.<String>get("ma")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(ten, ma));
				}
				if (predicates.size() > 0) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}

	public static Specification<ThongTinHopDong> quickSearch(final String search, final String ten,
			final String soHieu, final Long loaiHopDongId, final Integer trangThai, final LocalDate tuNgayKy,
			final LocalDate denNgayKy, final LocalDate thoiGianThTuNgay, final LocalDate thoiGianThDenNgay) {
		return new Specification<ThongTinHopDong>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6911993430219858311L;

			@Override
			public Predicate toPredicate(Root<ThongTinHopDong> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (search != null && !search.isEmpty()) {
					Predicate ten = cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase() + "%");
					Predicate soHieu = cb.like(cb.lower(root.<String>get("soHieu")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(ten, soHieu));
				}
				if (ten != null && !ten.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("ten")), "%" + ten.toLowerCase() + "%"));
				}
				if (soHieu != null && !soHieu.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("soHieu")), "%" + soHieu.toLowerCase() + "%"));
				}
				if (tuNgayKy != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("ngayKy").as(LocalDate.class), tuNgayKy));
				}
				if (denNgayKy != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("ngayKy").as(LocalDate.class), denNgayKy));
				}
				if (thoiGianThTuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("thoiGianThTuNgay").as(LocalDate.class),
							thoiGianThTuNgay));
				}
				if (thoiGianThDenNgay != null) {
					predicates.add(
							cb.lessThanOrEqualTo(root.get("thoiGianThDenNgay").as(LocalDate.class), thoiGianThDenNgay));
				}
				if (trangThai != null && trangThai > 0) {
					predicates.add(cb.equal(root.<String>get("trangThai"), trangThai));
				}
				if (loaiHopDongId != null && loaiHopDongId > 0) {
					predicates.add(cb.equal(root.<String>get("loaiHopDongId"), loaiHopDongId));
				}
				if (predicates.size() > 0) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
