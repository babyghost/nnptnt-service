package vn.dnict.microservice.nnptnt.qlchomeo.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.FileDinhKemKeHoach;
import vn.dnict.microservice.nnptnt.qlchomeo.dao.service.FileDinhKemKeHoachService;
import vn.dnict.microservice.nnptnt.qlchomeo.dao.service.KeHoachTiemPhongService;

@CrossOrigin
@Controller
@RequestMapping(value = "/qlchomeo/filedinhkemkehoach")
public class FileDinhKemKeHoachController {
	@Autowired
	private FileDinhKemKeHoachService fileDinhKemKeHoachService;
	@Autowired
	private KeHoachTiemPhongService keHoachTiemPhongService;
	
	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<FileDinhKemKeHoach>> danhSachFileDinhKemKeHoach(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "keHoachTiemPhongId", required = false) Long keHoachTiemPhongId,
			@RequestParam(name = "fileDinhKemId", required = false) Long fileDinhKemId) {

		Direction direction = Direction.ASC;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		Page<FileDinhKemKeHoach> pageFileDinhKemKeHoach = fileDinhKemKeHoachService.findAll(search, keHoachTiemPhongId,
				fileDinhKemId,
				PageRequest.of(page, size, direction, sortBy));

		return ResponseEntity.ok(pageFileDinhKemKeHoach);
	}

	@GetMapping(value = "/{keHoachTiemPhongId}")
	public ResponseEntity<FileDinhKemKeHoach> findBykeHoachTiemPhongId(@PathVariable("KeHoachTiemPhongId") Long keHoachTiemPhongId)
			throws EntityNotFoundException {
		Optional<FileDinhKemKeHoach> optional = fileDinhKemKeHoachService.findByKeHoachTiemPhongId(keHoachTiemPhongId);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(FileDinhKemKeHoach.class, "id", String.valueOf(keHoachTiemPhongId));
		}
		return ResponseEntity.ok(optional.get());
	}

}
