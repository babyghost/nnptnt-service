package vn.dnict.microservice.nnptnt.chomeo.data;

import lombok.Data;

@Data
public class ThongTinChoMeoImportData {
	
	private Long id;
	
	private String chuHo;
	
	private String diaChi;

	private String phuongXa;

	private String quanHuyen;

	private String dienThoai;

	private String loaiDongVat;

	private String tenConVat;

	private String namSinh;

	private String giong;

	private String mauLong;

	private String tinhBiet;

	private String trangThai;

	private Long thongTinChoMeoId;
	
	private Boolean trangThaiImport;
}
