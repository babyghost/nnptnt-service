package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.dao.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "chomeo_thongtinchomeo_import")
@EntityListeners(AuditingEntityListener.class)
@Data
public class ThongTinChoMeoImport {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "chuho", length = 250)
	private String chuHo;
	
	@Column(name = "diachi", length = 250)
	private String diaChi;
	
	@Column(name = "phuongxa", length = 250)
	private String phuongXa;
	
	@Column(name = "quanhuyen", length = 250)
	private String quanHuyen;
	
	@Column(name = "dienthoai", length = 20)
	private String dienThoai;
	
	@Column(name = "loaidongvat", length = 250)
	private String loaiDongVat;
	
	@Column(name = "tenconvat", length = 250)
	private String tenConVat;
	
	@Column(name = "namsinh", length = 4)
	private String namSinh;
	
	@Column(name = "giong", length = 50)
	private String giong;
	
	@Column(name = "maulong", length = 250)
	private String mauLong;
	
	@Column(name = "tinhbiet", length = 50)
	private String tinhBiet;
	
	@Column(name = "trangthai", length = 250)
	private String trangThai;
	
	@Column(name = "thongtinchomeo_id", unique = true, nullable = false)
	private Long thongTinChoMeoId;
	
	@Column(name = "trangthaiimport")
	private boolean trangThaiImport;

}