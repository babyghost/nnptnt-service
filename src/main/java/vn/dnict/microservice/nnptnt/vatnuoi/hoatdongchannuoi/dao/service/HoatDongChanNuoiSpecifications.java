package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.service;

import java.util.ArrayList; 
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;

public class HoatDongChanNuoiSpecifications {
	public static Specification<HoatDongChanNuoi> quickSearch(final String tenCoSo, final String tenChuCoSo, final String dienThoai,
			final Long quanHuyenId, final Long phuongXaId, final String nam, final Integer quy) {
		return new Specification<HoatDongChanNuoi>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<HoatDongChanNuoi> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(cb.equal(root.<String>get("daXoa"), false));
			
			if (tenCoSo != null && !tenCoSo.isEmpty()) {
				predicates.add(cb.like(cb.lower(root.join("coSoChanNuoi").<String>get("tenCoSo")), "%" + tenCoSo.toLowerCase() + "%"));
			}
			
			if (tenChuCoSo != null && !tenChuCoSo.isEmpty()) {
				predicates.add(cb.like(cb.lower(root.join("coSoChanNuoi").<String>get("tenChuCoSo")), "%" + tenChuCoSo.toLowerCase() + "%"));
			}
			
			if (dienThoai != null && !dienThoai.isEmpty()) {
				predicates.add(cb.like(cb.lower(root.join("coSoChanNuoi").<String>get("dienThoai")), "%" + dienThoai.toLowerCase() + "%"));
			}
			
			if (quanHuyenId != null && quanHuyenId > -1) {
				predicates.add(cb.equal(root.join("coSoChanNuoi").<String>get("quanHuyenId"), quanHuyenId));
			}
			
			if (phuongXaId != null && phuongXaId > -1) {
				predicates.add(cb.equal(root.join("coSoChanNuoi").<String>get("quanHuyenId"), phuongXaId));
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


	
	


