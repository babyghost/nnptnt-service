package vn.dnict.microservice.nnptnt.kehoach.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import vn.dnict.microservice.core.data.FileDinhKem;

@Data
public class TienDoNhiemVuNamData {
	private Long id;

	private Long nhiemVuNamId;

	private Integer tinhTrang;

	private Integer mucDoHoanThanh;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayBaoCao;

	@Size(max = 4000, message = "Nhập kết quả quá {max} ký tự")
	private String ketQua;

	private List<Long> fileDinhKemIds = new ArrayList<Long>();

	private FileDinhKem fileDinhKem = new FileDinhKem();
}
