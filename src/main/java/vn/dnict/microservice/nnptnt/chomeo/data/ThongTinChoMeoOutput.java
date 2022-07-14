package vn.dnict.microservice.nnptnt.chomeo.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.service.ChuQuanLyService;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.model.KeHoach2ChoMeo;

@Data
public class ThongTinChoMeoOutput {
	private Long id;
	private Long loaiDongVatId;
	private String tenConVat;	
	private String namSinh;
	private Long giongId;
	private String giong;
	private String loaiDongVat;
	private String mauLong;	
	private Integer tinhBiet;
	private Integer trangThai;	
	private Long chuQuanLyId;

	private List<KeHoach2ChoMeo> listKeHoach2ChoMeo;
	private ArrayList<LocalDate> ngayTiemPhongList;
	private LocalDate ngayTiemPhong;
}
