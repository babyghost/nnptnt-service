package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model;

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
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.model.KeHoach2ChoMeo;

@Entity
@Table(name = "chomeo_thongtinchomeo")
@EntityListeners(AuditingEntityListener.class)
@Data
public class ThongTinChoMeo {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(generator = "chomeo_thongtinchomeo_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "chomeo_thongtinchomeo_seq", sequenceName = "chomeo_thongtinchomeo_id_seq", allocationSize = 1)
	private Long id;
	
	@Column(name = "loaidongvat_id")
	private Long loaiDongVatId;

	@Column(name = "tenconvat", length = 250)
	private String tenConVat;

	@Column(name = "namsinh", length = 4)
	private String namSinh;
	
	@Column(name = "giong_id")
	private Long giongId;
	
	@Column(name = "maulong", length = 250)
	private String mauLong;
	
	@Column(name = "tinhbiet")
	private Integer tinhBiet;

	@Column(name = "trangthai")
	private Integer trangThai;
	
	@Column(name = "chuquanly_id")
	private Long chuQuanLyId;
	
	@JsonIgnore
	@Column(name = "daxoa")
	private boolean daXoa;

	@CreatedBy
	@Column(name = "nguoitao", length = 250)
	private String nguoiTao;

	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ngaytao")
	private LocalDateTime ngayTao;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "thongTinChoMeoId")
	@Where(clause = "daXoa = false")
	private List<KeHoach2ChoMeo> listKeHoach2ChoMeo;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "chuquanly_id", insertable=false, updatable=false)	
	@Where(clause = "daxoa = false")
	private ChuQuanLy chuQuanLy;
	@LastModifiedBy
	@Column(name = "nguoicapnhat", length = 250)
	private String nguoiCapNhat;

	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ngaycapnhat")
	private LocalDateTime ngayCapNhat;

}
