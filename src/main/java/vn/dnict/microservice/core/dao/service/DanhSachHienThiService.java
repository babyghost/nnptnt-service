package vn.dnict.microservice.core.dao.service;

import java.util.List;

import vn.dnict.microservice.core.dao.model.DanhSachHienThi;

public interface DanhSachHienThiService {

	public List<DanhSachHienThi> findByMaDanhSachAndNguoiSuDung(String maDanhSach, String nguoiSuDung);

}
