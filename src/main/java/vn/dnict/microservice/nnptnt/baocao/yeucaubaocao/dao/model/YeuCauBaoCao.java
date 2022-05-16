package vn.dnict.microservice.nnptnt.baocao.yeucaubaocao.dao.model;

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

import lombok.Data;
import vn.dnict.microservice.nnptnt.baocao.chitietbaocao.dao.model.ChiTietBaoCao;

@Entity
@Table(name = "bc_yeucaubaocao")
@EntityListeners(AuditingEntityListener.class)
@Data
public class YeuCauBaoCao {
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "ngayyeucau")
	private LocalDate ngayYeuCau;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "ngayhethan")
	private LocalDate ngayHetHan;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "ngayvanban")
	private LocalDate ngayVanBan;
	
	@Column(name = "tieude")
	private String tieuDe;
	
	@Column(name = "sovanban")
	private String soVanBan;
	

	@Column(name = "trangthai")
	private Integer trangThai;
	
	
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

	@Column(name = "linhvuc_id", length = 64)
	private Long linhVucId;

	@Column(name = "daxoa")
	private boolean daXoa;
	
	@JsonIgnore
	@OneToMany(mappedBy = "yeuCauBaoCaoId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ChiTietBaoCao> chiTietBaoCaos;
	

}
