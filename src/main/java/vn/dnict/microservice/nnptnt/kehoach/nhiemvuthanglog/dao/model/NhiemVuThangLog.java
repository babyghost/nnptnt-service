package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "qlkh_nhiemvuthanglog")
@EntityListeners(AuditingEntityListener.class)
@Data
public class NhiemVuThangLog {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "qlkh_nhiemvuthanglog_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "qlkh_nhiemvuthanglog_seq", sequenceName = "qlkh_nhiemvuthanglog_id_seq", allocationSize = 1)
	private Long id;

	@Column(name = "nhiemvuthang_id", nullable = false)
	private Long nhiemVuThangId;

	@Column(name = "tennhiemvu", length = 1000)
	private String tenNhiemVu;

	@Column(name = "canbothuchien_id", nullable = false)
	private Long canBoThucHienId;

	@Column(name = "tennguoicapnhat", length = 250)
	private String tenNguoiCapNhat;

	@Column(name = "tinhtrang")
	private Integer tinhTrang;

	@Column(name = "mucdohoanthanh")
	private Integer mucDoHoanThanh;

	@Column(name = "ketqua", length = 4000)
	private String ketQua;
	
	@LastModifiedBy
	@Column(name = "nguoicapnhat")
	private String nguoiCapNhat;

	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ngaycapnhat")
	private LocalDateTime ngayCapNhat;

	@JsonIgnore
	@Column(name = "daxoa")
	private boolean daXoa;
}
