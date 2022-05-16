package vn.dnict.microservice.nnptnt.baocao.kehoach.dao.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Entity
@Table(name = "bc_kehoach")
@EntityListeners(AuditingEntityListener.class)
@Data
public class KeHoach {
		@Id
		@Column(name = "id", unique = true, nullable = false)
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		
		@Column(name = "chitieu_id", length = 64)
		private Long chiTieuId;
		
		@Column(name = "nam", length = 16)
		private Integer nam;
		
		@Column(name = "kehoach", length = 64)
		private float keHoach;
		
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

		@Column(name = "dmlinhvuc_id", length = 64)
		private Long linhVucId;

		@Column(name = "daxoa")
		private boolean daXoa;
		

}
