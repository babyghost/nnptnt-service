package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model.ThongTinChoMeo;

public class ThongTinChoMeoSpecifications {
	public static Specification<ThongTinChoMeo> quickSearch(final Long loaiDongVatId,final Long giongId,final String tenChuHo, final String dienThoai, final LocalDate tuNgayTiemPhong, final LocalDate denNgayTiemPhong,final Long quanHuyenId, final Long phuongXaId,final Long keHoachTiemPhongId, final Integer trangThai, final Boolean trangThaiTiem) {
		return new Specification<ThongTinChoMeo>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<ThongTinChoMeo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (loaiDongVatId != null && loaiDongVatId > -1) {
					predicates.add(cb.equal(root.<String>get("loaiDongVatId"), loaiDongVatId));
				}
				if (giongId != null && giongId > -1) {
					predicates.add(cb.equal(root.<String>get("giongId"), giongId));
				}
				if (tuNgayTiemPhong != null ) {
					predicates.add(cb.greaterThanOrEqualTo(root.join("listKeHoach2ChoMeo").get("ngayTiemPhong").as(LocalDate.class), tuNgayTiemPhong));
				}
				if (denNgayTiemPhong != null ) {
					predicates.add(cb.lessThanOrEqualTo(root.join("listKeHoach2ChoMeo").get("ngayTiemPhong").as(LocalDate.class), denNgayTiemPhong));
				}
				if (trangThaiTiem != null) {
					predicates.add(cb.equal(root.join("listKeHoach2ChoMeo").<String>get("trangThaiTiem"), trangThaiTiem));
				}
				if (tenChuHo != null && !tenChuHo.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.join("chuQuanLy").<String>get("chuHo")), "%" + tenChuHo.toLowerCase() + "%"));

				}
				if (dienThoai != null && !dienThoai.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.join("chuQuanLy").<String>get("dienThoai")), "%" + dienThoai.toLowerCase() + "%"));

				}
				if (quanHuyenId != null && quanHuyenId > -1) {
					predicates.add(cb.equal(root.join("chuQuanLy").<String>get("quanHuyen_Id"), quanHuyenId));
				}
				if (phuongXaId != null && phuongXaId > -1) {
					predicates.add(cb.equal(root.join("chuQuanLy").<String>get("phuongXa_Id"), phuongXaId));
				}
				if (keHoachTiemPhongId != null && keHoachTiemPhongId > -1) {
					predicates.add(cb.equal(root.join("listKeHoach2ChoMeo").<String>get("keHoachTiemPhongId"), keHoachTiemPhongId));
				}
				if (trangThai != null) {
					predicates.add(cb.equal(root.<String>get("trangThai"), trangThai));
				}
				if (quanHuyenId != null && quanHuyenId > -1) {
					predicates.add(cb.equal(root.join("chuQuanLy").<String>get("quanHuyen_Id"), quanHuyenId));
				}
				if (phuongXaId != null && phuongXaId > -1) {
					predicates.add(cb.equal(root.join("chuQuanLy").<String>get("phuongXa_Id"), phuongXaId));
				}
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
				return null;
			}

		};
	}
	public static Specification<ThongTinChoMeo> baoCaoThongKe(final Long loaiDongVatId,final Long giongId,final String tenChuHo, final String dienThoai, final LocalDate tuNgayTiemPhong, final LocalDate denNgayTiemPhong,final Long quanHuyenId, final Long phuongXaId,final Long keHoachTiemPhongId, final Integer trangThai) {
		return new Specification<ThongTinChoMeo>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<ThongTinChoMeo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(cb.equal(root.<String>get("daXoa"), false));
				if (loaiDongVatId != null && loaiDongVatId > -1) {
					predicates.add(cb.equal(root.<String>get("loaiDongVatId"), loaiDongVatId));
				}
				if (giongId != null && giongId > -1) {
					predicates.add(cb.equal(root.<String>get("giongId"), giongId));
				}
				if (tuNgayTiemPhong != null ) {
					predicates.add(cb.greaterThanOrEqualTo(root.join("listKeHoach2ChoMeo").get("ngayTiemPhong").as(LocalDate.class), tuNgayTiemPhong));
				}
				if (denNgayTiemPhong != null ) {
					predicates.add(cb.lessThanOrEqualTo(root.join("listKeHoach2ChoMeo").get("ngayTiemPhong").as(LocalDate.class), denNgayTiemPhong));
				}
				if (tenChuHo != null && !tenChuHo.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.join("chuQuanLy").<String>get("chuHo")), "%" + tenChuHo.toLowerCase() + "%"));

				}
				if (dienThoai != null && !dienThoai.isEmpty()) {
					predicates
							.add(cb.like(cb.lower(root.join("chuQuanLy").<String>get("dienThoai")), "%" + dienThoai.toLowerCase() + "%"));

				}
				if (quanHuyenId != null && quanHuyenId > -1) {
					predicates.add(cb.equal(root.join("chuQuanLy").<String>get("quanHuyen_Id"), quanHuyenId));
				}
				if (phuongXaId != null && phuongXaId > -1) {
					predicates.add(cb.equal(root.join("chuQuanLy").<String>get("phuongXa_Id"), phuongXaId));
				}
				if (keHoachTiemPhongId != null && keHoachTiemPhongId > -1) {
					predicates.add(cb.equal(root.join("listKeHoach2ChoMeo").<String>get("keHoachTiemPhongId"), keHoachTiemPhongId));
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
