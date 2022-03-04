package vn.dnict.microservice.danhmuc.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;

public class DmPhuongXaSpecifications {
	public static Specification<DmPhuongXa> quickSearch(final String search, final Integer trangThai,
			final String quanHuyenCode, final String tinhThanhCode, final List<String> quanHuyenCodes,
			final List<String> tinhThanhCodes) {
		return new Specification<DmPhuongXa>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<DmPhuongXa> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (search != null && !search.isEmpty()) {
					Predicate ten = cb.like(cb.lower(root.<String>get("ten")), "%" + search.toLowerCase() + "%");
					Predicate ma = cb.like(cb.lower(root.<String>get("ma")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(ten, ma));
				}
				if (trangThai != null) {
					predicates.add(cb.equal(root.<Boolean>get("trangThai"), trangThai));
				}
				if (quanHuyenCode != null && !quanHuyenCode.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("quanHuyenCode")),
							"%" + quanHuyenCode.toLowerCase() + "%"));
				}
				if (tinhThanhCode != null && !tinhThanhCode.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tinhThanhCode")),
							"%" + tinhThanhCode.toLowerCase() + "%"));
				}
				if (tinhThanhCodes != null && !tinhThanhCodes.isEmpty()) {
					Expression<List<String>> vtinhThanhCodes = cb.literal(tinhThanhCodes);
					Expression<String> expression = root.<String>get("tinhThanhCode");
					Predicate inList = expression.in(vtinhThanhCodes);
					predicates.add(inList);
				}
				if (quanHuyenCodes != null && !quanHuyenCodes.isEmpty()) {
					Expression<List<String>> vquanHuyenCodes = cb.literal(quanHuyenCodes);
					Expression<String> expression = root.<String>get("quanHuyenCode");
					Predicate inList = expression.in(vquanHuyenCodes);
					predicates.add(inList);
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
		};
	}
}
