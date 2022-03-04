package vn.dnict.microservice.core.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dnict.microservice.core.business.CorePhanQuyenBusiness;
import vn.dnict.microservice.core.data.DanhSachMenuOutput;

@RestController
@RequestMapping(value = "/core/phanquyen")
public class CorePhanQuyenController {
	@Autowired
	private CorePhanQuyenBusiness corePhanQuyenBusiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<DanhSachMenuOutput> getChucNangs() {
		DanhSachMenuOutput danhSachMenuOutput = corePhanQuyenBusiness.getChucNang();
		return ResponseEntity.ok(danhSachMenuOutput);
	}

	@GetMapping(value = { "/routers" })
	public ResponseEntity<DanhSachMenuOutput> getRouterCon() {
		DanhSachMenuOutput danhSachMenuOutput = corePhanQuyenBusiness.getRouters();
		return ResponseEntity.ok(danhSachMenuOutput);
	}

	@GetMapping(value = { "/chucnang" })
	public ResponseEntity<List<String>> getChucNangIds(
			@RequestParam(name = "roleName", required = true) String roleName) {
		List<String> chucNangMas = corePhanQuyenBusiness.getChucNangMas(roleName);
		return ResponseEntity.ok(chucNangMas);
	}

}
