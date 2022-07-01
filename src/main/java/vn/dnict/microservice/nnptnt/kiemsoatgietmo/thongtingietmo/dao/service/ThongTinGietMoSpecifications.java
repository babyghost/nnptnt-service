package vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.dao.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.dao.model.ThongTinGietMo;

public class ThongTinGietMoSpecifications {
	public static Specification<ThongTinGietMo> quickSearch(List<String> tenCoSos, String tenChuCoSo, String dienThoai, LocalDate gietMoTuNgay,
			LocalDate gietMoDenNgay, Long quanHuyenId, Long phuongXaId) {
		return new Specification<ThongTinGietMo>() {
			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<ThongTinGietMo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (tenCoSos != null && !tenCoSos.isEmpty()) {
					Expression<List<String>> valuecoSoGietMoId = cb.literal(tenCoSos);
					Expression<String> expression = root.join("coSoGietMo").get("tenCoSo");
					Predicate inList = expression.in(valuecoSoGietMoId);
					predicates.add(inList);
				}
				
				if (tenChuCoSo != null && !tenChuCoSo.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.join("coSoGietMo").<String>get("tenChuCoSo")), "%" + tenChuCoSo.toLowerCase() + "%"));
				}
				
				if (dienThoai != null && !dienThoai.isEmpty()) {
					predicates.add(cb.like(cb.lower(root.join("coSoGietMo").<String>get("dienThoai")), "%" + dienThoai.toLowerCase() + "%"));
				}
				
				if (gietMoTuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("ngayThang").as(LocalDate.class),gietMoTuNgay));
				}
				if (gietMoDenNgay != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("ngayThang").as(LocalDate.class),gietMoDenNgay));
				}
				
				if (quanHuyenId != null && quanHuyenId > -1) {
					predicates.add(cb.equal(root.join("coSoGietMo").<String>get("quanHuyenId"), quanHuyenId));
				}
				
				if (phuongXaId != null && phuongXaId > -1) {
					predicates.add(cb.equal(root.join("coSoGietMo").<String>get("phuongXaId"), phuongXaId));
				}
				
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
			
		};
	}
	
	public static Specification<ThongTinGietMo> tongHopSoLuongNgay(final List<String> tenCoSos, final List<Long> loaiVatNuoiIds,
			final LocalDate gietMoTuNgay, LocalDate gietMoDenNgay) {
		return new Specification<ThongTinGietMo>() {
			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<ThongTinGietMo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (tenCoSos != null && !tenCoSos.isEmpty()) {
					Expression<List<String>> valuecoSoGietMoId = cb.literal(tenCoSos);
					Expression<String> expression = root.join("coSoGietMo").get("tenCoSo");
					Predicate inList = expression.in(valuecoSoGietMoId);
					predicates.add(inList);
				}
				
				if (loaiVatNuoiIds != null && !loaiVatNuoiIds.isEmpty()) {
					Expression<List<Long>> valueloaiVatNuoiIds = cb.literal(loaiVatNuoiIds);
					Expression<String> expression = root.join("soLuongGietMo").get("loaiVatNuoiId");
					Predicate inList = expression.in(valueloaiVatNuoiIds);
					predicates.add(inList);
				}
				
				if (gietMoTuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("ngayThang").as(LocalDate.class),gietMoTuNgay));
				}
				if (gietMoDenNgay != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("ngayThang").as(LocalDate.class),gietMoDenNgay));
				}
				
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}
			
		};
	}
}
