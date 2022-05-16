package vn.dnict.microservice.nnptnt.baocao.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class YeuCauBaoCaoData {
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")

	private LocalDate ngayYeuCau;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")

	private LocalDate ngayHetHan;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")

	private LocalDate ngayVanBan;

	private String tieuDe;

	private String soVanBan;

	private Integer trangThai;

	private Long linhVucId;
	
	private String linhVucTen;

	@NotEmpty(message = "Vui lòng chọn cơ sở đào tạo")
	private List<Long> donViIds = new ArrayList<Long>();

}
