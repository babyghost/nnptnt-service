package vn.dnict.microservice.nnptnt.dm.nhom.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.data.DmNhomData;
import vn.dnict.microservice.nnptnt.dm.nhom.bussiness.DmNhomBussiness;
@CrossOrigin
@RestController
@RequestMapping(value = "/dm/ocop/nhom")
public class DmNhomController {
	@Autowired
	DmNhomBussiness bussinessDmNhomBussiness;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DmNhomData>> findAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ngayCapNhat", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "DESC", required = false) String sortDir,
			@RequestParam(name = "ten", required = false) String ten,
			@RequestParam(name = "dmNganhHangId", required = false) Long dmNganhHangId,
			@RequestParam(name = "trangThai", required = false) Boolean trangThai) {

		Page<DmNhomData> pageDmNhomData = bussinessDmNhomBussiness.findAll(page, size, sortBy, sortDir, ten, dmNganhHangId, trangThai);
		return ResponseEntity.ok(pageDmNhomData);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DmNhomData> findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmNhomData YeuCauBaoCao = bussinessDmNhomBussiness.findById(id);
		return ResponseEntity.ok(YeuCauBaoCao);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<DmNhomData> create(@Valid @RequestBody DmNhomData DmNhomData) {
		DmNhomData = bussinessDmNhomBussiness.create(DmNhomData);
		return ResponseEntity.status(HttpStatus.CREATED).body(DmNhomData);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<DmNhomData> update(@PathVariable("id") Long id,
			@Valid @RequestBody DmNhomData dmNhomData) throws EntityNotFoundException {
		dmNhomData = bussinessDmNhomBussiness.update(id, dmNhomData);
		return ResponseEntity.ok(dmNhomData);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<DmNhomData> delete(@PathVariable("id") Long id) throws EntityNotFoundException {
		DmNhomData dmNhomData = bussinessDmNhomBussiness.delete(id);
		return ResponseEntity.ok(dmNhomData);
	}
}
