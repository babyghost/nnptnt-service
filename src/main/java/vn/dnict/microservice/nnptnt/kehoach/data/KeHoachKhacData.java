package vn.dnict.microservice.nnptnt.kehoach.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class KeHoachKhacData {

	private Long id;

	private String tenKeHoach;

	private Integer nam;

	private Double tongKinhPhi;

	private Integer trangThai;

	private Long donViLapKhId;

	private String donViLapKhTen;

	private Long phongBanLapKhId;

	private String phongBanLapKhTen;

	private Boolean isDieuChinh;

	private Boolean isLoaiNhiemVu;

	private Integer loaiKeHoach;

	private List<KeHoach2NhiemVuData> keHoach2NhiemVuDatas = new ArrayList<>();

	private KeHoach2QuyetDinhData keHoach2QuyetDinhData = new KeHoach2QuyetDinhData();
}
