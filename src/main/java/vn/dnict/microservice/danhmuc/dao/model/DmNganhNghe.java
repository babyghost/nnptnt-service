package vn.dnict.microservice.danhmuc.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "danhmuc_nganhnghe")
@Data
public class DmNganhNghe {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "ten", length = 100)
	private String ten;

	@Column(name = "ma", length = 20)
	private String ma;

	@Column(name = "trangthai")
	private Integer trangThai;

	@Column(name = "daxoa")
	private Integer daXoa;

}
