package vn.dnict.microservice.nnptnt.chomeo.data;

import lombok.Data;

@Data
public class ThongTinChoMeoImportInput {

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

	public ThongTinChoMeoImportInput() {
	}

	public ThongTinChoMeoImportInput(ThongTinChoMeoImportInput obj) {

		this.chuHo = obj.getChuHo();
		this.diaChi = obj.getDiaChi();
		this.phuongXa = obj.getPhuongXa();
		this.quanHuyen = obj.getQuanHuyen();
		this.dienThoai = obj.getDienThoai();
		this.loaiDongVat = obj.getLoaiDongVat();
		this.tenConVat = obj.getTenConVat();
		this.namSinh = obj.getNamSinh();
		this.giong = obj.getGiong();
		this.mauLong = obj.getMauLong();
		this.tinhBiet = obj.getTinhBiet();
		this.trangThai = obj.getTrangThai();
		this.thongTinChoMeoId = obj.getThongTinChoMeoId();

	}

}
