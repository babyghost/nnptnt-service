package vn.dnict.microservice.nnptnt.kehoach.data;

import java.util.List;

import lombok.Data;
import vn.dnict.microservice.core.dao.model.FileList;

@Data
public class FileDinhKemNhiemVuNamData {
	private Long id;
	
	private List<Long> listFileDinhKemId;
	private List<FileList> listFileDinhKem;
	
	private Long tienDoNhiemVuNamId;
	private String tienDoNhiemVuNamTen;	
}
