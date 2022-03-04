package vn.dnict.microservice.core.data.bean;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ChucVu {
	private Long id;
	private String ten;
	private String ma;
	private Boolean trangThai;
	private String nguoiCapNhat;
	private LocalDateTime ngayCapNhat;
	private String nguoiTao;
	private LocalDateTime ngayTao;
	private Boolean daXoa;
	private String appCode;
}
