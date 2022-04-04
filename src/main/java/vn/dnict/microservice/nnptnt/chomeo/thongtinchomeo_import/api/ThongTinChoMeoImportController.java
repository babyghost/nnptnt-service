//package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.api;
//
//import java.io.IOException;
//
//import java.util.HashMap;
//import java.util.List;
//
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import vn.dnict.microservice.exceptions.EntityNotFoundException;
//import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.business.ThongTinChoMeoImportBusiness;
//
//@RestController
//@RequestMapping(value = "/import/chomeo")
//public class ThongTinChoMeoImportController {
//	
//	@Autowired
//	private ThongTinChoMeoImportBusiness businessThongTinChoMeoImportBusiness;
//	
//	@GetMapping(value = { "/getdata/" })
//	public ResponseEntity<Boolean> getDataImport(
//			@RequestParam(name = "fileImportId", required = false) Long fileImportId)
//			throws EntityNotFoundException, IOException {
//		Boolean objectFlat = businessThongTinChoMeoImportBusiness.getDataImport( fileImportId);
//		return ResponseEntity.ok(objectFlat);
//	}
//	
////	@PostMapping(value = { "/save" })
////	public ResponseEntity<HashMap<String, String>> create(@Valid @RequestBody ImportChoMeo input)
////			throws EntityNotFoundException, IOException {
////		HashMap<String, String> result = businessThongTinChoMeoImportBusiness.saveImport(input);
////		return ResponseEntity.status(HttpStatus.CREATED).body(result);
////	}
//
//	@PostMapping(value = { "/save" })
//	public ResponseEntity<Integer> create(@Valid @RequestBody List<Long> ids)
//			throws EntityNotFoundException, IOException {
//		Integer count = businessThongTinChoMeoImportBusiness.save(ids);
//		return ResponseEntity.status(HttpStatus.CREATED).body(count);
//	}	
//}
//
