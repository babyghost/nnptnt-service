package vn.dnict.microservice.nnptnt.kehoach.kehoach2nhiemvu.dao.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.kehoach.kehoach2nhiemvu.dao.model.KeHoach2NhiemVu;

public class KeHoach2NhiemVuSpecifications {
	public static Specification<KeHoach2NhiemVu> quichSearch(final Long keHoachKhacId, final Long nhiemVuChaId, final Boolean isBanHanh,
			final Boolean isThucHien, final List<Long> nguonKinhPhiIds, final List<Integer> tinhTrangs, final LocalDate thoiGianThucHienTuNgay,
			final LocalDate thoiGianThucHienDenNgay, final LocalDate thoiGianThanhToanTuNgay, final LocalDate thoiGianThanhToanDenNgay,
			final List<Long> donViPhoiHopIds, final List<Long> phongBanPhoiHopIds, final Long donViThucHienId, final Long phongBanThucHienId) {
		return new Specification<KeHoach2NhiemVu>() {

			/**
			 *
			 */
			private static final long serialVersionUID = -4797263980304906209L;

			@Override
			public Predicate toPredicate(Root<KeHoach2NhiemVu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));

				query.distinct(true);
				query.orderBy(cb.asc(root.get("sapXep")));

				if (keHoachKhacId != null && keHoachKhacId > -1) {
					predicates.add(cb.equal(root.<String>get("keHoachKhacId"), keHoachKhacId));
				}
				if (nhiemVuChaId != null && nhiemVuChaId > -1) {
					predicates.add(cb.equal(root.<String>get("nhiemVuChaId"), nhiemVuChaId));
				} else if (nhiemVuChaId != null && nhiemVuChaId == -1) {
					predicates.add(cb.isNull(root.<String>get("nhiemVuChaId")));
				}
				if (nguonKinhPhiIds != null && !nguonKinhPhiIds.isEmpty()) {
					Expression<List<Long>> value = cb.literal(nguonKinhPhiIds);
					Expression<String> expression = root.join("khNvKhac2KinhPhiKhacs").get("dmNguonKinhPhiId");
					Predicate inList = expression.in(value);
					predicates.add(inList);
				}
				if (donViPhoiHopIds != null && !donViPhoiHopIds.isEmpty()) {
					Expression<List<Long>> value = cb.literal(donViPhoiHopIds);
					Expression<String> expression = root.join("khNvKhac2DonViPhoiHops").get("dmDonVi").get("id");
					Predicate inList = expression.in(value);
					predicates.add(inList);
				}
				if (phongBanPhoiHopIds != null && !phongBanPhoiHopIds.isEmpty()) {
					Expression<List<Long>> value = cb.literal(phongBanPhoiHopIds);
					Expression<String> expression = root.join("khNvKhac2DonViPhoiHops").get("dmPhongBan").get("id");
					Predicate inList = expression.in(value);
					predicates.add(inList);
				}
				if (tinhTrangs != null && !tinhTrangs.isEmpty()) {
					Expression<List<Integer>> value = cb.literal(tinhTrangs);
					Expression<String> expression = root.get("tinhTrang");
					Predicate inList = expression.in(value);
					predicates.add(inList);
				}
				if (isBanHanh != null) {
					predicates.add(cb.equal(root.<String>get("isBanHanh"), isBanHanh));
				}
				if (isThucHien != null) {
					Predicate isFalse = cb.equal(root.<String>get("isThemMoiThucHien"), isThucHien);
					Predicate isNull = cb.isNull(root.get("isThemMoiThucHien"));
					predicates.add(cb.or(isFalse, isNull));
				}
				if (thoiGianThanhToanTuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("thoiGianThanhToan").as(LocalDate.class), thoiGianThanhToanTuNgay));
				}
				if (thoiGianThanhToanDenNgay != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("thoiGianThanhToan").as(LocalDate.class), thoiGianThanhToanDenNgay));
				}
				if (thoiGianThucHienTuNgay != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("thoiGianThucHienTuNgay").as(LocalDate.class), thoiGianThucHienTuNgay));
				}
				if (thoiGianThucHienDenNgay != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("thoiGianThucHienDenNgay").as(LocalDate.class), thoiGianThucHienDenNgay));
				}
				if (phongBanThucHienId != null && phongBanThucHienId > -1) {
					predicates.add(cb.equal(root.<String>get("phongBanThucHienId"), phongBanThucHienId));
				}
				if (donViThucHienId != null && donViThucHienId > -1) {
					predicates.add(cb.equal(root.<String>get("donViThucHienId"), donViThucHienId));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[]{}));
				}
				return null;
			}

		};
	}
}
