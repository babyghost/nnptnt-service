package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.model;

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
@Table(name = "qlkh_nhiemvuthang2filebaocao")
@EntityListeners(AuditingEntityListener.class)
@Data
public class FileDinhKemNhiemVuThang {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "qlkh_nhiemvuthang2filebaocao_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "qlkh_nhiemvuthang2filebaocao_seq", sequenceName = "qlkh_nhiemvuthang2filebaocao_id_seq", allocationSize = 1)
	private Long id;

	@Column(name = "dinhkemfile_id", nullable = false)
	private Long fileDinhKemId;

	@Column(name = "tiendonvthang_id", nullable = false)
	private Long tienDoNvThangId;

	@JsonIgnore
	@Column(name = "daxoa")
	private Boolean daXoa;
	
	@CreatedBy
	@Column(name = "nguoitao")
	private String nguoiTao;

	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ngaytao")
	private LocalDateTime ngayTao;

	@LastModifiedBy
	@Column(name = "nguoicapnhat")
	private String nguoiCapNhat;

	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ngaycapnhat")
	private LocalDateTime ngayCapNhat;
}
