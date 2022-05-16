package vn.dnict.microservice.nnptnt.baocao.data;

import lombok.Data;

@Data
public class KeHoachData {
	private Long id;

	private Long linhVucId;

	private String linhVucTen;
	
	private Long chiTieuId;
	
	private float keHoach;

	private Integer nam;
}
