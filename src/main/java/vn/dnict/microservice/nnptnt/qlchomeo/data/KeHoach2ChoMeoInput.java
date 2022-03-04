package vn.dnict.microservice.nnptnt.qlchomeo.data;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class KeHoach2ChoMeoInput {

	private Long thongTinChoMeoId;
	
	private Long keHoachTiemPhongId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private LocalDate ngayTiemPhong;

	@NotNull(message = "Vui lòng chọn trạng thái tiêm")
	private boolean trangThaiTiem;

	}

