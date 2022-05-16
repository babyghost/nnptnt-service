package vn.dnict.microservice.nnptnt.baocao.chitieu.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.baocao.chitieu.dao.model.ChiTieu;

public class ChiTieuSpecifications {
	public static Specification<ChiTieu> quickSearch(final String ten,final Long chiTieuNamId ,final Integer nam
			){
		return new Specification<ChiTieu>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -175383039958263323L;

			@Override
			public Predicate toPredicate(Root<ChiTieu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (ten != null && !ten.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.<String>get("ten")), "%" + ten.toLowerCase() + "%"));

				}
				
				if (chiTieuNamId != null && chiTieuNamId > -1) {
					predicates.add(cb.equal(root.<String>get("chiTieuNamId"), chiTieuNamId));
				}
				if (nam != null && nam > -1) {
					predicates.add(cb.equal(root.<String>get("trangThai"), nam));
				}
			
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}


		};
		
	}
}
