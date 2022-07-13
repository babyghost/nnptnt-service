package vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.model;

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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model.NhiemVuNam;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;

@Entity
@Table(name = "qlkh_kehoachnam")
@EntityListeners(AuditingEntityListener.class)
@Data
public class KeHoachNam {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "qlkh_kehoachnam_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "qlkh_kehoachnam_seq", sequenceName = "qlkh_kehoachnam_id_seq", allocationSize = 1)
	private Long id;

	@Column(name = "tenkehoach", length = 500, nullable = false)
	private String tenKeHoach;

	@Column(name = "donvichutri_id", nullable = false)
	private Long donViChuTriId;

	@Column(name = "nam", nullable = false)
	private Integer nam;

	@Column(name = "sokyhieu", length = 100, nullable = false)
	private String soKyHieu;

	@Column(name = "trangthai", nullable = false)
	private Boolean trangThai;

	@Column(name = "ngaybanhanh", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayBanHanh;

	@JsonIgnore
	@Column(name = "daxoa")
	private Boolean daXoa;

	@Column(name = "ngaytao", nullable = false)
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayTao;

	@Column(name = "nguoitao", nullable = false)
	@LastModifiedBy
	private String nguoiTao;

	@Column(name = "nguoicapnhat", nullable = false)
	@LastModifiedBy
	private String nguoiCapNhat;

	@Column(name = "ngaycapnhat", nullable = false)
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime ngayCapNhat;
	
	@JsonIgnore
	@OneToMany(mappedBy = "keHoachNam")
	@Where(clause = "daxoa = false")
	private  List<NhiemVuNam> nhiemVuNams;
	
	@JsonIgnore
	@OneToMany(mappedBy = "nhiemVuNamId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Where(clause = "daxoa = false")
	private List<TienDoNhiemVuNam> tienDoNhiemVuNams;
}
