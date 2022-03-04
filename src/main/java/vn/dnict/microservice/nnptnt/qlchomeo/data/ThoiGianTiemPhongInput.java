package vn.dnict.microservice.nnptnt.qlchomeo.data;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class ThoiGianTiemPhongInput {

	@NotNull(message = "Vui lòng chọn địa điểm")
	private String diaDiem;

	private Long phuongXaId;
	
	private Long quanHuyenId;
	
	private Long keHoachTiemPhongId;

	private LocalDateTime thoiGianTiemTuNgay;
	
	private LocalDateTime thoiGianTiemDenNgay;


	
}
