package vn.dnict.microservice.core.dao.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DanhSachCoreChucNang {

	private Long id;

	private Long nhomChucNangChaId;

	private String ten;

	private String ma;

	private String icon;

	private String path;

	private Long chucNangId;

	private String chucNangTen;

	private String chucNangMa;
	
	private String chucNangPath;

	private Integer level;

	private Integer sapXep;

	private String component;

	private Boolean hidden;

	private Boolean alwaysShow;

	private Boolean noCache;

	private Boolean affix;

	private Boolean breadcrumb;

	private String activeMenu;

	private Integer chucNangSapXep;

	private String pathInfo;

	private String[] roles;

}
