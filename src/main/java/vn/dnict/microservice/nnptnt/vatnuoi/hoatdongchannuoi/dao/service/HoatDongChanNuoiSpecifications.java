package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;

public class HoatDongChanNuoiSpecifications {
	public static Specification<HoatDongChanNuoi> quickSearch(final String search, final Long loaiVatNuoiId, 
			final Long coSoChanNuoiId, final String nam, final Integer quy) {
		return new Specification<HoatDongChanNuoi>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<HoatDongChanNuoi> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(cb.equal(root.<String>get("daXoa"), false));
			if (search != null && !search.isEmpty()) {
				Predicate coSoChanNuoiId  = cb.like(cb.lower(root.<String>get("coSoChanNuoiId")), "%" + search.toLowerCase() + "%");
				Predicate loaiVatNuoiId  = cb.like(cb.lower(root.<String>get("loaiVatNuoiId")), "%" + search.toLowerCase() + "%");
				predicates.add(cb.or(loaiVatNuoiId, coSoChanNuoiId));
			}
			
			if (nam != null) {
				predicates.add(cb.equal(root.<String>get("nam"), nam));
			}
			
			if (quy != null) {
				predicates.add(cb.equal(root.<Integer>get("quy"), quy));
			}
			
			if (!predicates.isEmpty()) {
				return cb.and(predicates.toArray(new Predicate[] {}));
			}
			return null;
			}
		};
	}
}


	
	


