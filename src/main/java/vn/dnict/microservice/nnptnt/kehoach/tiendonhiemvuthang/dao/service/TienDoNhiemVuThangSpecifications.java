package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.model.TienDoNhiemVuThang;

public class TienDoNhiemVuThangSpecifications {
	public static Specification<TienDoNhiemVuThang> quickSearch(final String tenNguoiCapNhat, final Integer mucDoHoanThanh,
			final String ketQua) {
		return new Specification<TienDoNhiemVuThang>() {
			private static final long serialVersionUID = -5902884843433373982L;
			
			@Override
			public Predicate toPredicate(Root<TienDoNhiemVuThang> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				query.distinct(true);
				
				if(tenNguoiCapNhat != null && !tenNguoiCapNhat.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenNguoiCapNhat")), "%" + tenNguoiCapNhat.toLowerCase() + "%"));
				}				
				if(mucDoHoanThanh != null) {
					predicates.add(cb.equal(root.<Integer>get("mucDoHoanThanh"), mucDoHoanThanh));
				}				
				if(ketQua != null && !ketQua.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("ketQua")), "%" + ketQua.toLowerCase() + "%"));
				}				
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;				
			}
		};
	}
}
