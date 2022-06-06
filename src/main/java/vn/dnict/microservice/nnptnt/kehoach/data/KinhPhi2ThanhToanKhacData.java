package vn.dnict.microservice.nnptnt.kehoach.data;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import vn.dnict.microservice.core.data.FileDinhKem;

@Data
public class KinhPhi2ThanhToanKhacData {

	private Long id;

	private Long nhiemVu2KinhPhiId;

	private String soTienThanhToan;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayThanhToan;

	private List<Long> chungTuIds;

	private FileDinhKem fileDinhKem;

}
