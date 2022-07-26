package vn.dnict.microservice.nnptnt.kiemsoatgietmo.data;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import lombok.Data;
@Data
public class CoSoData {
	private Long coSoGietMoId;
	private String coSoTen;
	@Valid
	private List<SoLuongGietMoData> listSoLuongGietMo = new ArrayList<SoLuongGietMoData>();
}
