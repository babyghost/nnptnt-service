package vn.dnict.microservice.danhmuc.data;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import vn.dnict.microservice.danhmuc.dao.model.DmChucVu;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.model.DmGioiTinh;
import vn.dnict.microservice.danhmuc.dao.model.DmPhongBan;
@Data
public class DmCanBoInput {
	
	@NotBlank(message="Vui lòng nhập họ tên")
	@Size(max=150, message="Nhập họ tên quá ${max} ký tự")
	private String hoTen;
	
	@NotNull(message = "Vui lòng chọn phòng ban")
	private DmPhongBan phongBan;
	
	@NotNull(message = "Vui lòng chọn đơn vị")
	private DmDonVi donVi;
	
	private DmGioiTinh gioiTinh;
	
	private DmChucVu chucVu;
	
	@NotBlank(message="Vui lòng nhập email")
	@Email(message = "Vui lòng nhập đúng email")
	@Size(max=150, message="Nhập email quá ${max} ký tự")
	private String email;

	@Past(message = "Ngày sinh không thể bằng hoặc lớn hơn ngày hiện tại")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngaySinh;
	
	@Size(max=255, message="Nhập địa chỉ quá ${max} ký tự")
	private String diaChi;
	
	@Size(max=50, message="Nhập điện thoại quá ${max} ký tự")
	private String dienThoai;
	
	@Size(max=50, message="Nhập fax quá ${max} ký tự")
	private String fax;
	
	@Size(max=50, message="Nhập cmnd quá ${max} ký tự")
	private String cmndSo;
	
	@Past(message = "Ngày cấp cmnd không thể bằng hoặc lớn hơn ngày hiện tại")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate cmndNgayCap;
	
	@Size(max=255, message="Nhập nơi cấp cmnd quá ${max} ký tự")
	private String cmndNoiCap;
	
}
