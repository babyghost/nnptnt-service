package vn.dnict.microservice.nnptnt.kehoach.danhsachkehoach.dao.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DanhSachKeHoach {

	private String tenKeHoach;

	private Integer nam;

	private Double tongKinhPhi;

	private Long yeuCau2DonViId;

	private Long keHoachKhacId;

	private Long donViLapKhId;

	private Long phongBanLapKhId;

	private Long[] phongBanThucHienIds;

	private Long[] donViThucHienIds;

	private Integer loaiKeHoach;

	private Long trangThaiKeHoachId;

	private Integer trangThai;

	private Long[] nguoiNhanIds;

	private String soQuyetDinh;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayBanHanh;

	private String fileDinhKem;

	private Boolean isDieuChinh;

	private Boolean isBanHanh;

	private Long yeuCauLapKhId;

	private Long slNhiemVuDenHan;

}
