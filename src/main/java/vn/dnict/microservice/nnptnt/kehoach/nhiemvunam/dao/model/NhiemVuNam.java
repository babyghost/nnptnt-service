package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.model.KeHoachNam;

@Entity
@Table(name = "qlkh_nhiemvunam")
@EntityListeners(AuditingEntityListener.class)
@Data
public class NhiemVuNam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "kehoach_id")
	private Long keHoachNamId;

	@Column(name = "tennhiemvu", length = 500)
	private String tenNhiemVu;
	
	@Column(name = "nhiemvucha_id")
	private Long nhiemVuChaId;
	
	@Column(name = "sapxep")
	private Integer sapXep;
	
	@Column(name = "donviphoihop", length = 1000)
	private String donViPhoiHop;
	
	@Column(name = "tungay")
	private LocalDate tuNgay;

	@Column(name = "denngay")
	private LocalDate denNgay;
	
	@Column(name = "loainhiemvu_id")
	private Long loaiNhiemVuId;

	@Column(name = "ghichu")
	private String ghiChu;
	
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
	
	@JsonIgnore
	@Column(name = "daxoa")
	private boolean daXoa;
}
