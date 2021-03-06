package vn.dnict.microservice.core.data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CoreChucNangData {

	private Long id;

	@NotBlank(message = "Vui lòng nhập tên")
	@Size(max = 250, message = "Nhập tên quá {max} ký tự")
	private String ten;

	@NotBlank(message = "Vui lòng nhập mã")
	@Size(max = 50, message = "Nhập mã quá {max} ký tự")
	private String ma;

	@NotNull(message = "Vui lòng chọn nhóm chức năng")
	private Long nhomChucNangId;

	private Long nhomChucNangTen;

	private String moTa;

	@NotNull(message = "Vui lòng chọn trạng thái")
	private Boolean trangThai;

	@NotNull(message = "Vui lòng nhập sắp xếp")
	@Min(value = 0, message = "Nhập sắp xếp không nhỏ hơn {value}")
	@Max(value = 32767, message = "Nhập sắp xếp không lớn hơn {value}")
	private Integer sapXep;

	@Size(max = 500, message = "Nhập path quá {max} ký tự")
	private String path;

	@Size(max = 1000, message = "Nhập component quá {max} ký tự")
	private String component;

	private Boolean hidden;

	private Boolean alwaysShow;

	@Size(max = 1000, message = "Nhập title quá {max} ký tự")
	private String title;

	@Size(max = 250, message = "Nhập icon quá {max} ký tự")
	private String icon;

	private Boolean noCache;

	private Boolean affix;

	private Boolean breadcrumb;

	@Size(max = 500, message = "Nhập menu active quá {max} ký tự")
	private String activeMenu;

}
