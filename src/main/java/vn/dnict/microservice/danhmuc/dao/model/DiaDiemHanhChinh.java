package vn.dnict.microservice.danhmuc.dao.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DiaDiemHanhChinh {

	private Long phuongXaId;

	private String phuongXa;

	private String maPhuongXa;

	private Long quanHuyenId;

	private String maQuanHuyen;

	private String quanHuyen;

	private Long tinhThanhId;

	private String maTinhThanh;

	private String tinhThanh;

}
