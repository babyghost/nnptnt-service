package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.dao.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.model.KeHoachNam;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;

@Entity
@Table(name = "qlkh_nhiemvunam")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class NhiemVuNam {
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(generator = "qlkh_nhiemvunam_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "qlkh_nhiemvunam_seq", sequenceName = "qlkh_nhiemvunam_id_seq", allocationSize = 1)
	private Long id;

	@Column(name = "kehoach_id", nullable = false)
	private Long keHoachNamId;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kehoach_id" , referencedColumnName = "id",updatable = false, insertable = false)
	@Where(clause = "daxoa = false")
	private KeHoachNam keHoachNam;


	@Column(name = "tennhiemvu", length = 500, nullable = false)
	private String tenNhiemVu;

	@Column(name = "nhiemvucha_id")
	private Long nhiemVuChaId;

	@Column(name = "donviphoihop", length = 1000)
	private String donViPhoiHop;

	@Column(name = "tungay")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate tuNgay;

	@Column(name = "denngay")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate denNgay;

	@Column(name = "loainhiemvu_id", nullable = false)
	private Long loaiNhiemVuId;
	
	@JsonIgnore
	@OneToMany(mappedBy = "nhiemVuNamId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Where(clause = "daxoa = false")
	private List<TienDoNhiemVuNam> tienDoNhiemVuNams;

	@Column(name = "ghichu", length = 1000)
	private String ghiChu;

	@Column(name = "sapxep")
	private Integer sapXep;

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
	
	@Column(name = "danhso")
	private Integer danhSo;
}
