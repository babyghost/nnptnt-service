package vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.model.NhiemVuThang;

@Entity
@Table(name = "qlkh_kehoachthang")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class KeHoachThang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "donvichutri_id")
	private Long donViChuTriId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "thang")
	private LocalDate thang;
	
	@JsonIgnore
	@OneToMany(mappedBy = "keHoachThangId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<NhiemVuThang> nhiemVuThang;
	
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
	
	@JsonIgnore
	@Column(name = "daxoa")
	private Boolean daXoa;
}
