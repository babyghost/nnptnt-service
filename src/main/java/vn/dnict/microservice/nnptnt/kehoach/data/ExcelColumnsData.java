package vn.dnict.microservice.nnptnt.kehoach.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ExcelColumnsData {

	private String columnCode;

	private String columnName;

	private Boolean isHidden;

}
