package vn.dnict.microservice.nnptnt.chomeo.data;

import java.util.List;

import lombok.Data;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.dao.model.ThongTinChoMeoImport;
@Data
public class ImportChoMeo {
	private List<ThongTinChoMeoImport> listImport;
}
