package vn.dnict.microservice.nnptnt.chomeo.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.service.ChuQuanLyService;

@Data
public class ThongTinChoMeoOutput {

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
	
	private  ChuQuanLy chuQuanLy;
	private String chuHo;

}
