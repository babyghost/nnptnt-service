package vn.dnict.microservice.nnptnt.chomeo.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.model.KeHoach2ChoMeo;

@Data
public class ThongTinChoMeoData {

	private Long id;

	private Long loaiDongVatId;

	private String loaiDongVat;

	private String tenConVat;

	private String namSinh;

	private Long giongId;

	private String giong;

	private String mauLong;

	private Integer tinhBiet;

	private Integer trangThai;

	private Long chuQuanLyId;

	private String chuQuanLyTen;

	private String chuQuanLyDiaChi;

	private String dienthoai;
	
	private Long quanHuyenId;
	private String quanHuyenTen;
	private Long phuongXaId;
	private String phuongXaTen;
	private List<KeHoach2ChoMeo> listKeHoach2ChoMeo;
	
	private LocalDate ngayTiemPhong;
	private List<ThongTinChoMeoOutput> thongTinChoMeoOutput;
}
