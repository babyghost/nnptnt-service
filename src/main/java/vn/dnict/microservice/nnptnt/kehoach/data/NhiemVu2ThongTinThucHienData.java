package vn.dnict.microservice.nnptnt.kehoach.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import vn.dnict.microservice.core.data.FileDinhKem;

@Data
public class NhiemVu2ThongTinThucHienData {

	private Long id;

	private Long keHoach2NhiemVuId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayThucHienCapNhat;

	private String ketQuaThucHien;

	private Integer tienDo;

	private Boolean isDaGui;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayHoanThanh;

	private List<Long> fileDinhKemIds = new ArrayList<>();

	private FileDinhKem fileDinhKem = new FileDinhKem();

}
