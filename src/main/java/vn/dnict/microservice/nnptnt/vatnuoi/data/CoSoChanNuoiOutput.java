package vn.dnict.microservice.nnptnt.vatnuoi.data;

import lombok.Data;
import vn.dnict.microservice.nnptnt.vatnuoi.cosochannuoi.dao.model.CoSoChanNuoi;

@Data
public class CoSoChanNuoiOutput {
	private Long id;
	private String tenCoSo;
	private String tenChuCoSo;
	private String dienThoai;
	private String email;
	private String diaChi;
	private Long quanHuyenId;
	private String quanHuyenTen;
	private Long phuongXaId;
	private String phuongXaTen;
	private String ghiChu;
	
}
