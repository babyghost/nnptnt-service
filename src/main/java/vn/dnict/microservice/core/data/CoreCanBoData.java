package vn.dnict.microservice.core.data;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import vn.dnict.microservice.danhmuc.dao.model.DmCanBo;

@Data
public class CoreCanBoData {
	private Long chucVuId;
	private String chucVuTen;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate cmndNgayCap;
	private String cmndNoiCap;
	private String cmndSo;
	private String diaChi;
	private String dienThoai;
	private Long donViId;
	private String donViTen;
	private String email;
	private String fax;
	private Long gioiTinhId;
	private String gioiTinhTen;
	private String hoTen;
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngaySinh;
	private Long phongBanId;
	private String phongBanTen;
	
	public CoreCanBoData(DmCanBo canBo) {
		this.chucVuId = canBo.getChucVu() != null ? canBo.getChucVu().getId() : null;
		this.chucVuTen = canBo.getChucVu() != null ? canBo.getChucVu().getTen() : null;
		this.cmndNgayCap = canBo.getCmndNgayCap();
		this.cmndNoiCap = canBo.getCmndNoiCap();
		this.cmndSo = canBo.getCmndSo();
		this.diaChi = canBo.getDiaChi();
		this.dienThoai = canBo.getDienThoai();
		this.donViId = canBo.getDonVi() != null ? canBo.getDonVi().getId() : null;
		this.donViTen = canBo.getDonVi() != null ? canBo.getDonVi().getTenDonVi() : null;
		this.email = canBo.getEmail();
		this.fax = canBo.getFax();
		this.gioiTinhId = canBo.getGioiTinh() != null ? canBo.getGioiTinh().getId() : null;
		this.gioiTinhTen = canBo.getGioiTinh() != null ? canBo.getGioiTinh().getTen() : null;
		this.hoTen = canBo.getHoTen();
		this.id = canBo.getId();
		this.ngaySinh = canBo.getNgaySinh();
		this.phongBanId = canBo.getPhongBan() != null ? canBo.getPhongBan().getId() : null;
		this.phongBanTen = canBo.getPhongBan() != null ? canBo.getPhongBan().getTen() : null;
	}
}
