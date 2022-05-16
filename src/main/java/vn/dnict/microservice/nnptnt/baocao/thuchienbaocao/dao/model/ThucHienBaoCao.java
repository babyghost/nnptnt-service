package vn.dnict.microservice.nnptnt.baocao.thuchienbaocao.dao.model;

import java.time.LocalDate;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import vn.dnict.microservice.nnptnt.baocao.chitieu.dao.model.ChiTieu;

@Entity
@Table(name = "bc_thuchienbaocao")
@EntityListeners(AuditingEntityListener.class)
@Data
public class ThucHienBaoCao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "chitieu_id" )
	private Long chiTieuId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "chitieu_id", referencedColumnName = "id", updatable = false, insertable = false)
	private ChiTieu chiTieu;

	@Column(name = "thuchien")
	private float thucHien;

	@Column(name = "ngaythuchien")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayThucHien;
	
	@Column(name = "thangnam")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thangNam;
	
	@Column(name = "nguoicapnhat")
	@LastModifiedBy
	private String nguoiCapNhat;

	@Column(name = "ngaycapnhat")
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayCapNhat;

	@Column(name = "nguoitao")
	@CreatedBy
	private String nguoiTao;

	@Column(name = "ngaytao")
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayTao;

	@JsonIgnore
	@Column(name = "daxoa")
	private boolean daXoa;
}
