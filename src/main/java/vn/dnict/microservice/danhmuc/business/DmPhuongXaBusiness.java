package vn.dnict.microservice.danhmuc.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.danhmuc.dao.model.DmTinhThanh;
import vn.dnict.microservice.danhmuc.dao.service.DmPhuongXaService;
import vn.dnict.microservice.danhmuc.dao.service.DmQuanHuyenService;
import vn.dnict.microservice.danhmuc.dao.service.DmTinhThanhService;
import vn.dnict.microservice.danhmuc.data.DmPhuongXaData;
import vn.dnict.microservice.danhmuc.data.DmQuanHuyenData;
import vn.dnict.microservice.danhmuc.data.DmTinhThanhData;
import vn.dnict.microservice.danhmuc.data.validator.DmPhuongXaValidator;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@Service
public class DmPhuongXaBusiness {
	@Autowired
	private DmPhuongXaService dmPhuongXaService;
	@Autowired
	private DmTinhThanhService dmTinhThanhService;
	@Autowired
	private DmQuanHuyenService dmQuanHuyenService;
	@Autowired
	private DmPhuongXaValidator dmPhuongXaValidator;

	public Page<DmPhuongXaData> findAll(int page, int size, String sortBy, String sortDir, String search,
			String quanHuyenCode, String tinhThanhCode, List<Long> quanHuyenIds, List<Long> tinhThanhIds,
			Integer trangThai) {
		Direction direction = Direction.ASC;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		List<String> quanHuyenCodes = new ArrayList<String>();
		if (Objects.nonNull(quanHuyenIds) && !quanHuyenIds.isEmpty()) {
			List<DmQuanHuyen> dmQuanHuyens = dmQuanHuyenService.findByIdIn(quanHuyenIds);
			if (Objects.nonNull(dmQuanHuyens) && !dmQuanHuyens.isEmpty()) {
				for (DmQuanHuyen dmQuanHuyen : dmQuanHuyens) {
					quanHuyenCodes.add(dmQuanHuyen.getMa());
				}
			}
		}

		List<String> tinhThanhCodes = new ArrayList<String>();
		if (Objects.nonNull(tinhThanhIds) && !tinhThanhIds.isEmpty()) {
			List<DmTinhThanh> dmTinhThanhs = dmTinhThanhService.findByIdIn(tinhThanhIds);
			if (Objects.nonNull(dmTinhThanhs) && !dmTinhThanhs.isEmpty()) {
				for (DmTinhThanh dmTinhThanh : dmTinhThanhs) {
					tinhThanhCodes.add(dmTinhThanh.getMa());
				}
			}
		}

		final Page<DmPhuongXa> pageDanhMucPhuongXa = dmPhuongXaService.findAll(search, trangThai, quanHuyenCode,
				tinhThanhCode, quanHuyenCodes, tinhThanhCodes, PageRequest.of(page, size, direction, sortBy));
		final Page<DmPhuongXaData> pageDmPhuongXaData = pageDanhMucPhuongXa.map(this::convertToDmPhuongXaData);
		return pageDmPhuongXaData;
	}

	private DmPhuongXaData convertToDmPhuongXaData(DmPhuongXa dmPhuongXa) {
		DmPhuongXaData dmPhuongXaData = new DmPhuongXaData();
		dmPhuongXaData.setId(dmPhuongXa.getId());
		dmPhuongXaData.setTen(dmPhuongXa.getTen());
		dmPhuongXaData.setMa(dmPhuongXa.getMa());
		dmPhuongXaData.setQuanHuyenCode(dmPhuongXa.getQuanHuyenCode());
		dmPhuongXaData.setTinhThanhCode(dmPhuongXa.getTinhThanhCode());
		dmPhuongXaData.setTrangThai(dmPhuongXa.getTrangThai());
		List<DmTinhThanh> dmTinhThanhs = dmTinhThanhService.findByMaIgnoreCaseAndDaXoa(dmPhuongXa.getTinhThanhCode(),
				false);
		if (dmTinhThanhs != null && !dmTinhThanhs.isEmpty()) {
			DmTinhThanh dmTinhThanh = dmTinhThanhs.get(0);
			DmTinhThanhData dmTinhThanhData = new DmTinhThanhData();
			dmTinhThanhData.setId(dmTinhThanh.getId());
			dmTinhThanhData.setTen(dmTinhThanh.getTen());
			dmTinhThanhData.setMa(dmTinhThanh.getMa());
			dmTinhThanhData.setTrangThai(dmTinhThanh.getTrangThai());
			dmPhuongXaData.setDmTinhThanhData(dmTinhThanhData);
		}
		List<DmQuanHuyen> dmQuanHuyens = dmQuanHuyenService.findByMaIgnoreCaseAndDaXoa(dmPhuongXa.getQuanHuyenCode(),
				false);
		if (dmQuanHuyens != null && !dmQuanHuyens.isEmpty()) {
			DmQuanHuyen dmQuanHuyen = dmQuanHuyens.get(0);
			DmQuanHuyenData dmQuanHuyenData = new DmQuanHuyenData();
			dmQuanHuyenData.setId(dmQuanHuyen.getId());
			dmQuanHuyenData.setTen(dmQuanHuyen.getTen());
			dmQuanHuyenData.setMa(dmQuanHuyen.getMa());
			dmQuanHuyenData.setTinhThanhCode(dmQuanHuyen.getTinhThanhCode());
			dmQuanHuyenData.setTrangThai(dmQuanHuyen.getTrangThai());
			dmPhuongXaData.setDmQuanHuyenData(dmQuanHuyenData);
		}
		return dmPhuongXaData;

	}

	public DmPhuongXaData findById(@PathVariable("id") Long id) throws EntityNotFoundException {
		Optional<DmPhuongXa> optional = dmPhuongXaService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhuongXa.class, "id", String.valueOf(id));
		}
		DmPhuongXa dmPhuongXa = optional.get();
		return this.convertToDmPhuongXaData(dmPhuongXa);
	}

	public DmPhuongXaData create(@Valid @RequestBody DmPhuongXaData dmPhuongXaData, BindingResult result)
			throws MethodArgumentNotValidException {
		dmPhuongXaValidator.validate(dmPhuongXaData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		DmPhuongXa dmPhuongXa = new DmPhuongXa();
		dmPhuongXa.setTen(dmPhuongXaData.getTen());
		dmPhuongXa.setMa(dmPhuongXaData.getMa());
		dmPhuongXa.setQuanHuyenCode(dmPhuongXaData.getQuanHuyenCode());
		dmPhuongXa.setTinhThanhCode(dmPhuongXaData.getTinhThanhCode());
		dmPhuongXa.setTrangThai(dmPhuongXaData.getTrangThai());
		dmPhuongXa.setDaXoa(false);
		dmPhuongXa = dmPhuongXaService.save(dmPhuongXa);
		return this.convertToDmPhuongXaData(dmPhuongXa);
	}

	public DmPhuongXaData update(Long id, DmPhuongXaData dmPhuongXaData, BindingResult result)
			throws EntityNotFoundException, MethodArgumentNotValidException {
		Optional<DmPhuongXa> optional = dmPhuongXaService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhuongXa.class, "id", String.valueOf(id));
		}
		dmPhuongXaValidator.validate(dmPhuongXaData, result);
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		DmPhuongXa dmPhuongXa = optional.get();
		dmPhuongXa.setTen(dmPhuongXaData.getTen());
		dmPhuongXa.setMa(dmPhuongXaData.getMa());
		dmPhuongXa.setTinhThanhCode(null);
		List<DmTinhThanh> tinhThanhs = dmTinhThanhService.findByMaIgnoreCaseAndDaXoa(dmPhuongXaData.getTinhThanhCode(),
				false);
		if (Objects.nonNull(tinhThanhs) && !tinhThanhs.isEmpty()) {
			DmTinhThanh dmTinhThanh = tinhThanhs.get(0);
			dmPhuongXa.setTinhThanhCode(dmTinhThanh.getMa());
		}
		dmPhuongXa.setQuanHuyenCode(null);
		List<DmQuanHuyen> quanHuyens = dmQuanHuyenService.findByMaIgnoreCaseAndDaXoa(dmPhuongXaData.getQuanHuyenCode(),
				false);
		if (Objects.nonNull(quanHuyens) && !quanHuyens.isEmpty()) {
			DmQuanHuyen dmQuanHuyen = quanHuyens.get(0);
			dmPhuongXa.setQuanHuyenCode(dmQuanHuyen.getMa());
		}
		dmPhuongXa.setTrangThai(dmPhuongXaData.getTrangThai());
		return this.convertToDmPhuongXaData(dmPhuongXa);
	}

	public DmTinhThanh findByTinhThanhById(Long id) throws EntityNotFoundException {
		Optional<DmPhuongXa> optional = dmPhuongXaService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhuongXa.class, "id", String.valueOf(id));
		}
		DmPhuongXa phuongXa = optional.get();
		DmTinhThanh tinhThanh = new DmTinhThanh();
		List<DmTinhThanh> listTinhThanh = dmTinhThanhService.findByMa(phuongXa.getTinhThanhCode());
		if (listTinhThanh != null && !listTinhThanh.isEmpty()) {
			tinhThanh = listTinhThanh.get(0);
		}
		return tinhThanh;
	}

	public DmQuanHuyen findByQuanHuyenById(Long id) throws EntityNotFoundException {
		Optional<DmPhuongXa> optional = dmPhuongXaService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhuongXa.class, "id", String.valueOf(id));
		}
		DmPhuongXa phuongXa = optional.get();
		DmQuanHuyen quanHuyen = new DmQuanHuyen();
		List<DmQuanHuyen> listQuanHuyen = dmQuanHuyenService.findByMa(phuongXa.getQuanHuyenCode());
		if (listQuanHuyen != null && !listQuanHuyen.isEmpty()) {
			quanHuyen = listQuanHuyen.get(0);
		}
		return quanHuyen;
	}

	public DmQuanHuyen findByQuanHuyenByMa(String ma) throws EntityNotFoundException {
		DmQuanHuyen quanHuyen = new DmQuanHuyen();
		List<DmQuanHuyen> dmQuanHuyens = dmQuanHuyenService.findByMaIgnoreCaseAndDaXoa(ma, false);
		if (dmQuanHuyens != null && !dmQuanHuyens.isEmpty()) {
			quanHuyen = dmQuanHuyens.get(0);
		}
		return quanHuyen;
	}

	public DmTinhThanh findByTinhThanhByMa(String ma) throws EntityNotFoundException {
		DmTinhThanh tinhThanh = new DmTinhThanh();
		List<DmTinhThanh> dmTinhThanhs = dmTinhThanhService.findByMaIgnoreCaseAndDaXoa(ma, false);
		if (dmTinhThanhs != null && !dmTinhThanhs.isEmpty()) {
			tinhThanh = dmTinhThanhs.get(0);
		}
		return tinhThanh;
	}

	public DmPhuongXa delete(Long id) throws EntityNotFoundException {
		Optional<DmPhuongXa> optional = dmPhuongXaService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmPhuongXa.class, "id", String.valueOf(id));
		}
		DmPhuongXa dmPhuongXa = optional.get();
		dmPhuongXa.setDaXoa(true);
		dmPhuongXa = dmPhuongXaService.save(dmPhuongXa);
		return dmPhuongXa;
	}
}
