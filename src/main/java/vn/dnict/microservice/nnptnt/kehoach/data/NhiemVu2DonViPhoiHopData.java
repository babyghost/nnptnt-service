package vn.dnict.microservice.nnptnt.kehoach.data;

import lombok.Data;

@Data
public class NhiemVu2DonViPhoiHopData {

	private Long id;

	private Long khKhac2NhiemVuId;

	private Long donViId;

	private String donViTen;

	private Long phongBanId;

	private String phongBanTen;

}
