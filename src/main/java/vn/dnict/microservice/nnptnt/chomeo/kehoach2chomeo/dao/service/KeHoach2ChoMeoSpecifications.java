package vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.model.KeHoach2ChoMeo;

public class KeHoach2ChoMeoSpecifications {
	public static Specification<KeHoach2ChoMeo> quickSearch(final Long thongTinChoMeoId, final Long keHoachTiemPhongId,final LocalDate ngayTiemPhongTuNgay, final LocalDate ngayTiemPhongDenNgay, final boolean trangThaiTiem) {
		return new Specification<KeHoach2ChoMeo>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<KeHoach2ChoMeo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (thongTinChoMeoId != null && thongTinChoMeoId > -1) {
					predicates.add(cb.equal(root.<String>get("thongTinChoMeoId"), thongTinChoMeoId));
				}
				if (keHoachTiemPhongId != null && keHoachTiemPhongId > -1) {
					predicates.add(cb.equal(root.<String>get("keHoachTiemPhongId"), keHoachTiemPhongId));
				}

				if (ngayTiemPhongTuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("ngayTiemPhong").as(LocalDate.class),ngayTiemPhongTuNgay));
				}
				if (ngayTiemPhongDenNgay != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("ngayTiemPhong").as(LocalDate.class), ngayTiemPhongDenNgay));
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