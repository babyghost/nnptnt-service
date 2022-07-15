package vn.dnict.microservice.nnptnt.dm.phuongthucbaolanhhd.dao.model;

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

import lombok.Data;

@Entity
@Table(name = "qlhd_dmphuongthucbaolanhhd")
@EntityListeners(AuditingEntityListener.class)
@Data
public class DmPhuongThucBaoLanhHd {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ten", length = 255, nullable = false)
	private String ten;

	@Column(name = "ma", length = 20, nullable = false)
	private String ma;

	@Column(name = "type", nullable = false)
	private Integer type;

	@Column(name = "trangthai", nullable = false)
	private Boolean trangThai;

	@Column(name = "nguoicapnhat", length = 150, nullable = false)
	@LastModifiedBy
	private String nguoiCapNhat;

	@Column(name = "ngaycapnhat", nullable = false)
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayCapNhat;

	@Column(name = "nguoitao", length = 150, nullable = false)
	@CreatedBy
	private String nguoiTao;

	@Column(name = "ngaytao", nullable = false)
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayTao;

	@Column(name = "daxoa", nullable = false)
	private Boolean daXoa;
}
