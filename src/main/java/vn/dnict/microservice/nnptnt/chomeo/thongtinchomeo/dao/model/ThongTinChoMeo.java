package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "chomeo_thongtinchomeo")
@EntityListeners(AuditingEntityListener.class)
@Data
public class ThongTinChoMeo {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "loaidongvat_id")
	private Long loaiDongVatId;

	@Column(name = "tenconvat", length = 250)
	private String tenConVat;

	@Column(name = "namsinh", length = 4)
	private String namSinh;
	
	@Column(name = "giong_id")
	private Long giongId;
	
	@Column(name = "maulong", length = 250)
	private String mauLong;
	
	@Column(name = "tinhbiet")
	private Integer tinhBiet;

	@Column(name = "trangthai")
	private Integer trangThai;
	
	@Column(name = "chuquanly_id")
	private Long chuQuanLyId;
	
	@JsonIgnore
	@Column(name = "daxoa")
	private boolean daXoa;

	@CreatedBy
	@Column(name = "nguoitao", length = 250)
	private String nguoiTao;

	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ngaytao")
	private LocalDateTime ngayTao;

	@LastModifiedBy
	@Column(name = "nguoicapnhat", length = 250)
	private String nguoiCapNhat;

	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ngaycapnhat")
	private LocalDateTime ngayCapNhat;

}
