package vn.dnict.microservice.nnptnt.vatnuoi.data;

import java.util.List;

import lombok.Data;
@Data
public class NamData {

//	private String nam;
	
	private Integer quy;
	private List<ThongTinHoatDongChanNuoiOutput> listHoatDongChanNuoi;
}
