package vn.dnict.microservice.danhmuc.api;

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

import vn.dnict.microservice.danhmuc.dao.model.DiaDiemHanhChinh;
import vn.dnict.microservice.danhmuc.dao.service.DiaDiemHanhChinhService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@CrossOrigin
@Controller
@RequestMapping(value = "/danhmuc/diadiemhanhchinh")
public class DiaDiemHanhChinhController {
	@Autowired
	private DiaDiemHanhChinhService diaDiemHanhChinhService;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<DiaDiemHanhChinh>> danhSachDiaDiemHanhChinh(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "20", required = false) int size,
			@RequestParam(name = "sortBy", defaultValue = "ten", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "tinhThanhId", required = false) Long tinhThanhId,
			@RequestParam(name = "quanHuyenId", required = false) Long quanHuyenId,
			@RequestParam(name = "phuongXaId", required = false) Long phuongXaId,
			@RequestParam(name = "tinhThanhCode", required = false) String tinhThanhCode,
			@RequestParam(name = "quanHuyenCode", required = false) String quanHuyenCode,
			@RequestParam(name = "phuongXaCode", required = false) String phuongXaCode) {

		Direction direction = Direction.ASC;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		Page<DiaDiemHanhChinh> pageDiaDiemHanhChinh = diaDiemHanhChinhService.findAll(search, tinhThanhId,
				tinhThanhCode, quanHuyenId, quanHuyenCode, phuongXaId, phuongXaCode,
				PageRequest.of(page, size, direction, sortBy));

		return ResponseEntity.ok(pageDiaDiemHanhChinh);
	}

	@GetMapping(value = "/{phuongXaId}")
	public ResponseEntity<DiaDiemHanhChinh> findByPhuongXaId(@PathVariable("phuongXaId") Long phuongXaId)
			throws EntityNotFoundException {
		Optional<DiaDiemHanhChinh> optional = diaDiemHanhChinhService.findByPhuongXaId(phuongXaId);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DiaDiemHanhChinh.class, "id", String.valueOf(phuongXaId));
		}
		return ResponseEntity.ok(optional.get());
	}

}
