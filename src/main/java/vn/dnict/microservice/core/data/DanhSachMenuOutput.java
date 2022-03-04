package vn.dnict.microservice.core.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DanhSachMenuOutput {

	private String email;

	private List<String> roles;

	private List<DanhSachNhomChucNangOutput> danhSachNhomChucNangOutputs = new ArrayList<>();

}
