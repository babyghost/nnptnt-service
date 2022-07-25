package vn.dnict.microservice.nnptnt.baocao.data;

import java.util.List;

import lombok.Data;

@Data
public class ChiTieuKeHoachData {
	private Long linhVucId;
	private String linhVucTen; 
	private Integer nam;
	private List<KeHoachData> listKeHoach;
	
}
