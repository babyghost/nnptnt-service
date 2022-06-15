package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;

public class TienDoNhiemVuNamSpecifications {
	public static Specification<TienDoNhiemVuNam> quickSearch(final LocalDate ngayBaoCao, final Integer tinhTrang,
			final Integer mucDoHoanThanh) {
		return new Specification<TienDoNhiemVuNam>() {
			private static final long serialVersionUID = -5902884843433373982L;
			
			@Override
			public Predicate toPredicate(Root<TienDoNhiemVuNam> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				query.distinct(true);
				
				if (ngayBaoCao != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("ngayBanHanh").as(LocalDate.class), ngayBaoCao));
				}
				if (tinhTrang != null) {
					predicates.add(cb.equal(root.<Integer>get("tinhTrang"), tinhTrang));
				}
				if (mucDoHoanThanh != null) {
					predicates.add(cb.equal(root.<Integer>get("mucDoHoanThanh"), mucDoHoanThanh));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
		};
	}

}
