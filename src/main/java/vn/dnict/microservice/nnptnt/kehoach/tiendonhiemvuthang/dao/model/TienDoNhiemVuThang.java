package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "qlkh_tiendonhiemvuthang")
@EntityListeners(AuditingEntityListener.class)
@Data
public class TienDoNhiemVuThang {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "qlkh_tiendonhiemvuthang_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "qlkh_tiendonhiemvuthang_seq", sequenceName = "qlkh_tiendonhiemvuthang_id_seq", allocationSize = 1)
	private Long id;

	@Column(name = "mucdohoanthanh", nullable = false)
	private Integer mucDoHoanThanh;

	@Column(name = "ketqua", length = 4000)
	private String ketQua;

	@Column(name = "tennguoicapnhat", length = 150)
	private String tenNguoiCapNhat;

	@JsonIgnore
	@Column(name = "daxoa")
	private Boolean daXoa;
	
	@CreatedBy
	@Column(name = "nguoitao", length = 250)
	private String nguoiTao;
	
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ngaytao")
	private LocalDateTime ngayTao;
	
	@Column(name = "nguoicapnhat", length = 250)
	private String nguoiCapNhat;

	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ngaycapnhat")
	private LocalDateTime ngayCapNhat;
}
