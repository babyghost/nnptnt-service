package vn.dnict.microservice.core.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import vn.dnict.microservice.core.dao.model.FileList;

@Data
public class FileDinhKem {

	private List<Long> ids = new ArrayList<>();

	private List<FileList> fileLists = new ArrayList<>();
}
