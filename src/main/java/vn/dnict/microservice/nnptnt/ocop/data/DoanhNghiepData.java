package vn.dnict.microservice.nnptnt.ocop.data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DoanhNghiepData {

	private Long id;


	private String ten;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayCap;
	

	private String diaChi;
	

	private Long quanHuyenId;
	
	private String quanHuyenTen;
	

	private Long phuongXaId;
	
	private String phuongXaTen;
	

	private String dienThoai;
	

	private String email;
	

	private String website;
	

	private String chuSoHuu;
	

	private String nguoiDaiDien;
	

	private Long loaiHinhId;
	
	private String loaiHinhTen;
	

	private Long loaiDoanhNghiepId;
	
	private String loaiDoanhNghiepTen;

	private Long nganhNgheId;
	
	private String nganhNgheTen;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayThanhLap;
	

	private String chuSoHuuDiaChi;
	

	private String chuSoHuuDienThoai;
	

	private String chuSoHuuEmail;
	

	private String nguoiDaiDienDienThoai;
	

	private String nguoiDaiDienEmail;
	

	private String giayPhepKinhDoanh;
	
	

	private Long tinhThanhId;
	
	private String tinhThanhTen;
	

	private Integer trangThai;




}
