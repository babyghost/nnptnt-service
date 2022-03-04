package vn.dnict.microservice.core.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DanhSachChucNangOutput {

	private Long id;

	private String chucNangTen;

	private String chucNangMa;

	private Integer sapXep;

	private String path;

	private String component;

	private Boolean hidden;

	private Boolean alwaysShow;

	private String title;

	private String icon;

	private Boolean noCache;

	private Boolean affix;

	private Boolean breadcrumb;

	private String activeMenu;
	
	private List<String> roles = new ArrayList<>();

}
