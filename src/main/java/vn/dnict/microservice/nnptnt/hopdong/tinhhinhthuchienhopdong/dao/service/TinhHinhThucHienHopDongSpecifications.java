package vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.model.TinhHinhThucHienHopDong;


public class TinhHinhThucHienHopDongSpecifications {
	public static Specification<TinhHinhThucHienHopDong> findByDaXoaAndSearch(final String search) {
		return new Specification<TinhHinhThucHienHopDong>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 8158104837151351366L;

			@Override
			public Predicate toPredicate(Root<TinhHinhThucHienHopDong> root, CriteriaQuery<?> query,
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
}
