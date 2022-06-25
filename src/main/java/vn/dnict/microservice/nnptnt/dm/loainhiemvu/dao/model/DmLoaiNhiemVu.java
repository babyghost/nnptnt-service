package vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.model;

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
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "qlkh_dmloainhiemvu")
@EntityListeners(AuditingEntityListener.class)
@Data
public class DmLoaiNhiemVu {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "qlkh_dmloainhiemvu_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "qlkh_dmloainhiemvu_seq", sequenceName = "qlkh_dmloainhiemvu_id_seq", allocationSize = 1)
	private Long id;

	@Column(name = "ten", length = 250, nullable = false)
	private String ten;

	@Column(name = "ma", length = 20)
	private String ma;

	@Column(name = "sapxep")
	private Integer sapXep;

	@Column(name = "trangthai")
	private Boolean trangThai;

	@JsonIgnore
	@Column(name = "daxoa")
	private Boolean daXoa;
	
	@Column(name = "nguoitao")
	@CreatedBy
	private String nguoiTao;
	
	@Column(name = "ngaytao")
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayTao;
	
	@Column(name = "nguoicapnhat")
	@LastModifiedBy
	private String nguoiCapNhat;
	
	@Column(name = "ngaycapnhat")
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayCapNhat;
}
