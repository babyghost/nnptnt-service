package vn.dnict.microservice.nnptnt.qlchomeo.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ThongTinChoMeoInput {
	
	private Long loaiDongVatId;
	
	@NotBlank(message = "Vui lòng nhập tên")
	private String tenConVat;
	
	@NotBlank(message = "Vui lòng nhập tên")
	private String namSinh;
	
	private Long giongId;
	
	@NotBlank(message = "Vui lòng nhập tên")
	private String mauLong;
	
	private Integer tinhBiet;

	@NotNull(message = "Vui lòng chọn trạng thái")
	private Integer trangThai;
	
	private Long chuQuanLyId;

}
