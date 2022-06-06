package vn.dnict.microservice.nnptnt.kehoach.data;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import vn.dnict.microservice.core.data.FileDinhKem;

@Data
public class KeHoach2QuyetDinhData {
	
	private Long id;
	
	private Long keHoachKhacId;
	
	private List<Long> quyetDinhIds;
	
	@Size(max = 50, message = "Nhập số quyết định quá {max} ký tự")
	private String soQuyetDinh;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate ngayBanHanh;
	
	private Boolean isHienTai;
	
	private FileDinhKem fileDinhKem = new FileDinhKem();
	
}
