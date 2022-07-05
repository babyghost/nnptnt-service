package vn.dnict.microservice.nnptnt.ocop.data;

import java.util.List;

import lombok.Data;
import vn.dnict.microservice.core.dao.model.FileList;

@Data
public class SanPhamData {

	private Long id;

	private String ten;

	private String moTa;

	private Integer trangThai;

	private Long doanhNghiepId;

	private String doanhNghiepTen;

	private Long fileDinhKemId;

	private Long nganhHangId;

	private String nganhHangTen;

	private Long phanNhomId;

	private String phanNhomTen;

	private Long nhomId;

	private String nhomTen;

	private Boolean daXoa;
	
	private String quyetDinh;
	
	private List<Long> fileDinhKemIds;
	
	private List<FileList> listFileDinhKem;
	
	private Long phanHangId;
	
	private String phanHangTen;
}
