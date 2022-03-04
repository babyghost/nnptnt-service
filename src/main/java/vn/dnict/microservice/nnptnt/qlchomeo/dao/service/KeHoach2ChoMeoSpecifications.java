package vn.dnict.microservice.nnptnt.qlchomeo.dao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.KeHoach2ChoMeo;

public class KeHoach2ChoMeoSpecifications {
	public static Specification<KeHoach2ChoMeo> quickSearch(final String search, final boolean trangThaiTiem) {
		return new Specification<KeHoach2ChoMeo>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<KeHoach2ChoMeo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), true));
				if (search != null && !search.isEmpty()) {
					Predicate thongTinChoMeoId  = cb.like(cb.lower(root.<String>get("thongTinChoMeoId")), "%" + search.toLowerCase() + "%");
					Predicate keHoachTiemPhongId  = cb.like(cb.lower(root.<String>get("keHoachTiemPhongId")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(thongTinChoMeoId, keHoachTiemPhongId));
				}
				if (Objects.nonNull(trangThaiTiem)) {
					predicates.add(cb.equal(root.<String>get("trangThaiTiem"), trangThaiTiem));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}

	
}