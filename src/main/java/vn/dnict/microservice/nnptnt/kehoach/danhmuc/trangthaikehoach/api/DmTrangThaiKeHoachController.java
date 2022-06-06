package vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.business.DmTrangThaiKeHoachBusiness;
import vn.dnict.microservice.nnptnt.kehoach.danhmuc.trangthaikehoach.dao.model.DmTrangThaiKeHoach;
import vn.dnict.microservice.nnptnt.kehoach.data.DmTrangThaiKeHoachData;

@CrossOrigin
@RestController
@RequestMapping(value = "/danhmuc/trangthaikehoach")
public class DmTrangThaiKeHoachController {
	@Autowired
	protected DmTrangThaiKeHoachBusiness businessDmTrangThaiKeHoachBusiness;

	@GetMapping(value = {"/", ""})
	public ResponseEntity<Page<DmTrangThaiKeHoach>> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "trangThai", required = false) Boolean trangThai,
			@RequestParam(name = "isMacDinh", required = false) Boolean isMacDinh, @RequestParam(name = "search", required = false) String search) {

		Page<DmTrangThaiKeHoach> pageDmTrangThaiKeHoach = businessDmTrangThaiKeHoachBusiness.findAll(page, size, sortBy, sortDir, search);
		return ResponseEntity.ok(pageDmTrangThaiKeHoach);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmTrangThaiKeHoachData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmTrangThaiKeHoachData dmTrangThaiKeHoachData = businessDmTrangThaiKeHoachBusiness.findById(id);
		return ResponseEntity.ok(dmTrangThaiKeHoachData);
	}

}
