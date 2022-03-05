package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.dao.model.ThongTinChoMeoImport;

public class ThongTinChoMeoImportSpecifications {
	public static Specification<ThongTinChoMeoImport> quickSearch(final String search, final Long thongTinChoMeoId,
			final String trangThai) {
		return new Specification<ThongTinChoMeoImport>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<ThongTinChoMeoImport> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				if (search != null && !search.isEmpty()) {
					Predicate chuHo = cb.like(cb.lower(root.<String>get("chuHo")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(chuHo));
				}
				if (trangThai != null) {
					predicates.add(cb.equal(root.<String>get("trangThai"), trangThai));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
}
