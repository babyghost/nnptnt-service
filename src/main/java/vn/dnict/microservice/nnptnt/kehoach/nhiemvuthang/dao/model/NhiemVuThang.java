package vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.model.KeHoachThang;

@Entity
@Table(name = "qlkh_nhiemvuthang")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class NhiemVuThang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "tennhiemvu", length = 1000)
	private String tenNhiemVu;
	
	@Column(name = "kehoachthang_id")
	private Long keHoachThangId;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "kehoachthang_id", insertable=false, updatable=false)	
	@Where(clause = "daxoa = false")
	private KeHoachThang keHoachThang;
	
	@Column(name = "canbothuchien_id")
	private Long canBoThucHienId;
	
	@Column(name = "donviphoihop", length = 1000)
	private String donViPhoiHop;
	
	@Column(name = "thoigian")
	private LocalDate thoiGian;
	
	@Column(name = "ghichu", length = 1000)
	private String ghiChu;
	
	@Column(name = "is_nhiemvuthangtruoc")
	private Boolean isNhiemVuThangTruoc;
	
	@Column(name = "nhiemvuthangtruoc_id")
	private Long nhiemVuThangTruocId;
	
	@Column(name = "tinhtrang")
	private Integer tinhTrang;
	
	@Column(name = "tiendonhiemvu_id")
	private Long tienDoNhiemVuId;
	
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
