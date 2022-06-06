package vn.dnict.microservice.nnptnt.kehoach.cauhinhdsnguoinhan.dao.model;

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
@Table(name = "modulechung_kh_cauhinhdanhsachnguoinhan")
@EntityListeners(AuditingEntityListener.class)
@Data
public class CauHinhDanhSachNguoiNhan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "donvi_id")
	private Long donViId;
	
	@Column(name = "phongban_id")
	private Long phongBanId;
	
	@Column(name = "nguoinhan_id")
	private Long nguoiNhanId;
	
	@Column(name = "trangthaikehoach_id")
	private Long trangThaiKeHoachId;
	
}
