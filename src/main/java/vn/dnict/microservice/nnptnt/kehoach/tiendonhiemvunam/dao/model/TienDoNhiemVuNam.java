package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model;

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

@Entity
@Table(name = "qlkh_tiendonhiemvunam")
@EntityListeners(AuditingEntityListener.class)
@Data
public class TienDoNhiemVuNam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nhiemvunam_id")
	private Long nhiemVuNamId;
	
	@Column(name = "tinhtrang")
	private Boolean tinhTrang;
	
	@Column(name = "mucdohoanthanh")
	private Integer mucDoHoanThanh;
	
	@Column(name = "ngaybaocao")
	private LocalDate ngayBaoCao;
	
	@Column(name = "ketqua", length = 4000)
	private String ketQua;
	
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
