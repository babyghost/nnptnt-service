package vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;

public class HoatDongChanNuoiSpecifications {
	public static Specification<HoatDongChanNuoi> quickSearch(String search, Integer donViTinh, Integer soLuongNuoi,
			String mucDichNuoi, LocalDate thoiGianBatDauNuoi, LocalDate thoiGianXuat, Integer slVatNuoiXuat,
			Float sanLuongXuat, String ghiChu, Long loaiVatNuoiId,Long coSoChanNuoiId) {
		return new Specification<HoatDongChanNuoi>() {

			private static final long serialVersionUID = -4615834727542993669L;

			@Override
			public Predicate toPredicate(Root<HoatDongChanNuoi> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(cb.equal(root.<String>get("daXoa"), true));
			if (search != null && !search.isEmpty()) {
				Predicate coSoChanNuoiId  = cb.like(cb.lower(root.<String>get("coSoChanNuoiId")), "%" + search.toLowerCase() + "%");
//				Predicate namChanNuoiId  = cb.like(cb.lower(root.<String>get("namChanNuoiId")), "%" + search.toLowerCase() + "%");
				Predicate loaiVatNuoiId  = cb.like(cb.lower(root.<String>get("loaiVatNuoiId")), "%" + search.toLowerCase() + "%");
				predicates.add(cb.or(loaiVatNuoiId, coSoChanNuoiId));
			}
			
			
			if (!predicates.isEmpty()) {
				return cb.and(predicates.toArray(new Predicate[] {}));
			}
			return null;

			}
			

		};
	}


	}


	
	


