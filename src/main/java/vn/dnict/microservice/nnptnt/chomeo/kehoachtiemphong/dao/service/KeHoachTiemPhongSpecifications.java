package vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.model.KeHoachTiemPhong;

public class KeHoachTiemPhongSpecifications {
	public static Specification<KeHoachTiemPhong> quickSearch(final String search, final String noiDung,final String soKeHoach, final LocalDateTime ngayBanHanh,LocalDateTime ngayDuKienTuNgay, LocalDateTime ngayDuKienDenNgay, final String tenKeHoach) {
		return new Specification<KeHoachTiemPhong>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<KeHoachTiemPhong> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), true));
				if (search != null && !search.isEmpty()) {
					Predicate tenkehoach = cb.like(cb.lower(root.<String>get("tenKeHoach")), "%" + search.toLowerCase() + "%");
					Predicate sokehoach  = cb.like(cb.lower(root.<String>get("soKeHoach")), "%" + search.toLowerCase() + "%");
					Predicate ngaybanhanh  = cb.like(cb.lower(root.<String>get("ngayBanHanh")), "%" + search.toLowerCase() + "%");
					Predicate ngaydukientungay  = cb.like(cb.lower(root.<String>get("ngayDuKienTuNgay")), "%" + search.toLowerCase() + "%");
					Predicate ngaydukiendenngay  = cb.like(cb.lower(root.<String>get("ngayDuKienDenNgay")), "%" + search.toLowerCase() + "%");
					predicates.add(cb.or(tenkehoach, sokehoach, ngaybanhanh));
				}
				if (noiDung != null) {
					predicates.add(cb.equal(root.<String>get("noiDung"), noiDung));
				}

				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}

}
