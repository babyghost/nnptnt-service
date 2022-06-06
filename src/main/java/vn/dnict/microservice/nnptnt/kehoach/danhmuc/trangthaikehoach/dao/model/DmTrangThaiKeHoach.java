package vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.dao.model;

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
@Table(name = "modulechung_kh_dmtrangthaikehoach")
@EntityListeners(AuditingEntityListener.class)
@Data
public class DmTrangThaiKeHoach {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "ten", length = 500, nullable = false)
	private String ten;
	
	@Column(name = "thuhoi_id")
	private Long thuHoiId;
	
	@Column(name = "dieuchinh_id")
	private Long dieuChinhId;
	
	@Column(name = "thuhoikhtc_id")
	private Long thuHoiKhtcId;
	
}
