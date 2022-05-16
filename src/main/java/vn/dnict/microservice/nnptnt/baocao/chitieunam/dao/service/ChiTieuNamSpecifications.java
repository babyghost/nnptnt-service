package vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.model.ChiTieuNam;

public class ChiTieuNamSpecifications {
	public static Specification<ChiTieuNam> quickSearch(final Long linhVucId ,final Integer nam
			){
		return new Specification<ChiTieuNam>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -175383039958263323L;

			@Override
			public Predicate toPredicate(Root<ChiTieuNam> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (linhVucId != null && linhVucId > -1) {
					predicates.add(cb.equal(root.<String>get("linhVucId"), linhVucId));
				}
				if (nam != null && nam > -1) {
					predicates.add(cb.equal(root.<String>get("nam"), nam));
				}
			
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}


		};
		
	}
}
