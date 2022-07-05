package vn.dnict.microservice.nnptnt.ocop.tieuchinam.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.ocop.tieuchinam.dao.model.TieuChiNam;

public class TieuChiNamSpecifications {
	public static Specification<TieuChiNam> quickSearch(final Integer nam,final Long phanNhomId,final Long nganhHangId,final Long nhomId,final Integer trangThai
			){
		return new Specification<TieuChiNam>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -175383039958263323L;

			@Override
			public Predicate toPredicate(Root<TieuChiNam> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
		
				if (nam != null && nam > -1) {
					predicates.add(cb.equal(root.<String>get("nam"), nam));
				}
				if (phanNhomId != null && phanNhomId > -1) {
					predicates.add(cb.equal(root.<String>get("phanNhomId"), phanNhomId));
				}
				if (nganhHangId != null && nganhHangId > -1) {
					predicates.add(cb.equal(root.<String>get("nganhHangId"), nganhHangId));
				}
				if (nhomId != null && nhomId > -1) {
					predicates.add(cb.equal(root.<String>get("nhomId"), nhomId));
				}
				if (trangThai != null && trangThai > -1) {
					predicates.add(cb.equal(root.<String>get("trangThai"), trangThai));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}


		};
		
	}
	public static Specification<TieuChiNam> searchChiTiet(final Integer nam,final Long phanNhomId,final Long nganhHangId,final Long nhomId
			){
		return new Specification<TieuChiNam>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -175383039958263323L;

			@Override
			public Predicate toPredicate(Root<TieuChiNam> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
		
				if (nam != null && nam > -1) {
					predicates.add(cb.equal(root.<String>get("nam"), nam));
				}
				if (phanNhomId != null && phanNhomId > -1) {
					predicates.add(cb.equal(root.<String>get("phanNhomId"), phanNhomId));
				}
				if (nganhHangId != null && nganhHangId > -1) {
					predicates.add(cb.equal(root.<String>get("nganhHangId"), nganhHangId));
				}
				if (nhomId != null && nhomId > -1) {
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
