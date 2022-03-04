package vn.dnict.microservice.core.data;

import java.util.List;

import lombok.Data;
import vn.dnict.microservice.core.dao.model.DanhSachHienThi;

@Data
public class DanhSachHienThiInput {
	private String maDanhSach;
	private String nguoiSuDung;
	private List<DanhSachHienThi> listDanhSachHienThi;

}