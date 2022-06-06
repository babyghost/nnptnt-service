package vn.dnict.microservice.nnptnt.kehoach.cauhinhdsnguoinhan.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.kehoach.cauhinhdsnguoinhan.dao.model.CauHinhDanhSachNguoiNhan;

public class CauHinhDanhSachNguoiNhanSpecifications {
	
	public static Specification<CauHinhDanhSachNguoiNhan> quichSearch(final Long donViId, final Long phongBanId, final Long trangThaiKeHoachId) {
		return new Specification<CauHinhDanhSachNguoiNhan>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -5902884843433373982L;
			
			@Override
			public Predicate toPredicate(Root<CauHinhDanhSachNguoiNhan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>();
				// predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (donViId != null && donViId > -1) {
					predicates.add(cb.equal(root.<String>get("donViId"), donViId));
				}
				if (phongBanId != null && phongBanId > -1) {
					predicates.add(cb.equal(root.<String>get("phongBanId"), phongBanId));
				}
				if (trangThaiKeHoachId != null && trangThaiKeHoachId > -1) {
					predicates.add(cb.equal(root.<String>get("trangThaiKeHoachId"), trangThaiKeHoachId));
				}
				
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[]{}));
				}
				return null;
			}
			
		};
	}
}
