package vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;

public class ChuQuanLySpecifications {
	public static Specification<ChuQuanLy> quickSearch(final String chuHo,final String diaChi,
			final Integer dienThoai){
		return new Specification<ChuQuanLy>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -175383039958263323L;

			@Override
			public Predicate toPredicate(Root<ChuQuanLy> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (chuHo != null && !chuHo.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("chuHo")), "%" + chuHo.trim().toLowerCase() + "%"));
				}
				if(diaChi != null && !diaChi.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("diaChi")), "%" + diaChi.trim().toLowerCase() + "%"));
				}
				if (dienThoai != null) {
					predicates.add(cb.equal(root.<String>get("dienthoai"), dienThoai));
				}
			
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}


		};
		
	}
}
