package vn.dnict.microservice.core.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RouterData {

	private String path;

	private Object component;

	private String redirect;

	private String name;

	private MetaData meta;

	private List<RouterData> children = new ArrayList<RouterData>();

	private Boolean hidden;

	private Boolean alwaysShow;

}
