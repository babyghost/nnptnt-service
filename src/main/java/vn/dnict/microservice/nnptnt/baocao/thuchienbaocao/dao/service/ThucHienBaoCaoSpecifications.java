package vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.model.ThucHienBaoCao;

public class ThucHienBaoCaoSpecifications {
	public static Specification<ThucHienBaoCao> quickSearch(final Long linhVucId ,final LocalDate thangNam, final LocalDate ngayThucHien
			){
		return new Specification<ThucHienBaoCao>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -175383039958263323L;

			@Override
			public Predicate toPredicate(Root<ThucHienBaoCao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (linhVucId != null && linhVucId > -1) {
					predicates.add(cb.equal(root.join("chiTieu").get("chiTieuNam").get("linhVucId"), linhVucId));
				}
				if (thangNam != null) {
					predicates.add(cb.equal(root.get("thangNam").as(LocalDate.class), thangNam));
				}
				if (ngayThucHien != null) {
					predicates.add(cb.equal(root.get("ngayThucHien").as(LocalDate.class), ngayThucHien));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}


		};
		
	}
	public static Specification<ThucHienBaoCao> thongKe(final Long linhVucId ,final LocalDate thangNam, final LocalDate ngayThucHien, LocalDate thangBatDau, LocalDate thangKetThuc, LocalDate thangBatDauTN, LocalDate thangKetThucTN
			){
		return new Specification<ThucHienBaoCao>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -175383039958263323L;

			@Override
			public Predicate toPredicate(Root<ThucHienBaoCao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (linhVucId != null && linhVucId > -1) {
					predicates.add(cb.equal(root.join("chiTieu").get("chiTieuNam").get("linhVucId"), linhVucId));
				}
				if (thangNam != null) {
					predicates.add(cb.equal(root.get("thangNam").as(LocalDate.class), thangNam));
				}
				if (ngayThucHien != null) {
					predicates.add(cb.equal(root.get("ngayThucHien").as(LocalDate.class), ngayThucHien));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}


		};
		
	}
}
