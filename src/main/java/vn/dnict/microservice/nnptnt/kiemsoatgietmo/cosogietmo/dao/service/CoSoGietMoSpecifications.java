package vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.model.CoSoGietMo;

public class CoSoGietMoSpecifications {
	public static Specification<CoSoGietMo> quickSearch(final String tenCoSo, final String tenChuCoSo, final String dienThoai,
			final String email, final Long phuongXaId, Long quanHuyenId) {
		return new Specification<CoSoGietMo>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<CoSoGietMo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (tenCoSo != null && !tenCoSo.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.<String>get("tenCoSo")), "%" + tenCoSo.toLowerCase() + "%"));
				}
				
				if (tenChuCoSo != null && !tenChuCoSo.isEmpty()) {
					predicates.add(cb.equal(root.<String>get("tenChuCoSo"), tenChuCoSo));
				}
				
				if (dienThoai != null && !dienThoai.isEmpty()) {
					predicates.add(cb.equal(root.<String>get("dienThoai"), dienThoai));
				}
				
				if (email != null && !email.isEmpty()) {
					predicates.add(cb.equal(root.<String>get("email"), email));
				}
				
				if (phuongXaId != null) {
					predicates.add(cb.equal(root.<Long>get("phuongXaId"), phuongXaId));
				}
				
				if (quanHuyenId != null) {
					predicates.add(cb.equal(root.<Long>get("quanHuyenId"), quanHuyenId));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
