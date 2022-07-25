package vn.dnict.microservice.nnptnt.baocao.data;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ChiTieuThucHienData {
	private Long linhVucId;
	private String linhVucTen;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate thangNam;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayThucHien;
	private List<ThucHienBaoCaoData> listBaoCao;
}
