package vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.dao.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.dao.model.YeuCauBaoCao;

public class YeuCauBaoCaoSpecifications {
	public static Specification<YeuCauBaoCao> quickSearch(final String tieuDe,final  LocalDate ngayYeuCauTuNgay,final  LocalDate ngayYeuCauDenNgay,final LocalDate thoiHanTuNgay, final LocalDate thoiHanDenNgay,final  Long linhVucId, final Integer trangThai
			){
		return new Specification<YeuCauBaoCao>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -175383039958263323L;

			@Override
			public Predicate toPredicate(Root<YeuCauBaoCao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (linhVucId != null && linhVucId > -1) {
					predicates.add(cb.equal(root.<String>get("linhVucId"), linhVucId));
				}
				if (trangThai != null && trangThai > -1) {
					predicates.add(cb.equal(root.<String>get("trangThai"), trangThai));
				}
				if (ngayYeuCauTuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("ngayYeuCau").as(LocalDate.class),ngayYeuCauTuNgay));
				}
				if (ngayYeuCauDenNgay != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("ngayYeuCau").as(LocalDate.class), ngayYeuCauDenNgay));
				}if (thoiHanTuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("ngayHetHan").as(LocalDate.class),thoiHanTuNgay));
				}
				if (thoiHanDenNgay != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("ngayHetHan").as(LocalDate.class), thoiHanDenNgay));
				}
				if (tieuDe != null && !tieuDe.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.<String>get("tieuDe")), "%" + tieuDe.toLowerCase() + "%"));

				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				
				return null;
			}


		};
		
	}
}
