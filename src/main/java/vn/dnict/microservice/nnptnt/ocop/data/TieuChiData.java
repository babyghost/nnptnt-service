package vn.dnict.microservice.nnptnt.ocop.data;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import lombok.Data;

@Data
public class TieuChiData {
	private Long id;

	private String ten;

	private String diem;

	private Long chaId;

	private Long tieuChiNamId;

	private Long phanTieuChiId;

	private Boolean isTinhDiem;

	private Boolean isTinhTong;

	private Integer kieuDanhSo;

	private Integer nam;
	
	@Valid
	private List<TieuChiData> children = new ArrayList<TieuChiData>();
}
