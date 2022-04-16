
package vn.dnict.microservice.nnptnt.dm.giong.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.dm.giong.dao.model.DmGiong;

public class DmGiongSpecifications {
	public static Specification<DmGiong> quickSearch( String search,Long loaiDongVatId, Integer trangThai) {
		return new Specification<DmGiong>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<DmGiong> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), 0));
				if (search != null && !search.isEmpty()) {
					Predicate ten = cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase() + "%");
					Predicate ma  = cb.like(cb.lower(root.<String>get("ma")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(ten, ma));
				}
				if (trangThai != null) {
					predicates.add(cb.equal(root.<String>get("trangThai"), trangThai));
				}
				if (loaiDongVatId != null) {
					predicates.add(cb.equal(root.<String>get("loaiDongVatId"), loaiDongVatId));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}

	
}