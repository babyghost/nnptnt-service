package vn.dnict.microservice.danhmuc.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.dnict.microservice.danhmuc.dao.model.DmCapDonVi;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.model.DmLoaiDonVi;
import vn.dnict.microservice.danhmuc.dao.service.DmCapDonViService;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.danhmuc.dao.service.DmLoaiDonViService;
import vn.dnict.microservice.danhmuc.data.DmDonViInput;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@Service
public class DmDonViBusiness {
	@Autowired
	private DmDonViService donViService;
	@Autowired
	private DmLoaiDonViService loaiDonViService;
	@Autowired
	private DmCapDonViService capDonViService;

	@GetMapping(value = { "/", "" })
	public Page<DmDonVi> findAll(int page, int size, String sortBy, String sortDir, String search,
			String tenDonVi, String diaChi, String soDienThoai, String email, Long donViChaId, Long capId,
			Long loaiDonViId) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<DmDonVi> pageDonVi = donViService.findAll(search, tenDonVi, diaChi, soDienThoai, email, donViChaId,
				capId, loaiDonViId, PageRequest.of(page, size, direction, sortBy));
		return pageDonVi;
	}

	public DmDonVi findDonViChaByDonViId(Long id) throws EntityNotFoundException {
		Optional<DmDonVi> optional = donViService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmDonVi.class, "id", String.valueOf(id));
		}
		DmDonVi donVi = optional.get();
		Optional<DmDonVi> optionalDonViCha = donViService.findById(donVi.getDonViChaId());
		DmDonVi donViCha = null;
		if (optionalDonViCha.isPresent()) {
			donViCha = optionalDonViCha.get();
		}
		return donViCha;
	}

	public DmCapDonVi findCapDonViByDonViId(Long id) throws EntityNotFoundException {
		Optional<DmDonVi> optional = donViService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmDonVi.class, "id", String.valueOf(id));
		}
		DmDonVi donVi = optional.get();
		Optional<DmCapDonVi> optionalCapDonVi = capDonViService.findById(donVi.getCapDonViId());
		if (!optionalCapDonVi.isPresent()) {
			throw new EntityNotFoundException(DmCapDonVi.class, "id", String.valueOf(id));
		}
		return optionalCapDonVi.get();
	}

	public DmLoaiDonVi findLoaiDonViByDonViId(Long id) throws EntityNotFoundException {
		Optional<DmDonVi> optional = donViService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmDonVi.class, "id", String.valueOf(id));
		}
		DmDonVi donVi = optional.get();
		Optional<DmLoaiDonVi> optionalLoaiDonVi = loaiDonViService.findById(donVi.getLoaiDonViId());
		if (!optionalLoaiDonVi.isPresent()) {
			throw new EntityNotFoundException(DmLoaiDonVi.class, "id", String.valueOf(id));
		}
		return optionalLoaiDonVi.get();
	}

	public DmDonVi findById(Long id) throws EntityNotFoundException {
		Optional<DmDonVi> optional = donViService.findById(id);
		if (!optional.isPresent()) {
			//throw new EntityNotFoundException(DonVi.class, "id", String.valueOf(id));
			return new DmDonVi();
		}
		return optional.get();
	}

	public List<DmDonVi> findByIdIn(@PathVariable("ids") List<Long> ids) {
		return donViService.findByIdIn(ids);
	}

	public DmDonVi create(DmDonViInput donViInput) {
		DmDonVi donVi = new DmDonVi();
		donVi.setDaXoa(false);
		donVi.setDonViChaId(donViInput.getDonViChaId());
		donVi.setTenDonVi(donViInput.getTenDonVi());
		donVi.setTenVietTat(donViInput.getTenVietTat());
		donVi.setSoDienThoai(donViInput.getSoDienThoai());
		donVi.setDiaChi(donViInput.getDiaChi());
		donVi.setFax(donViInput.getFax());
		donVi.setEmail(donViInput.getEmail());
		donVi.setLoaiDonViId(donViInput.getLoaiDonViId());
		donVi.setCapDonViId(donViInput.getCapDonViId());
		//donVi.setAppCode(appCode);
		donVi = donViService.save(donVi);
		return donVi;
	}

	public DmDonVi update(Long id, DmDonViInput donViInput) throws EntityNotFoundException {
		Optional<DmDonVi> optional = donViService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmDonVi.class, "id", String.valueOf(id));
		}
		DmDonVi donVi = optional.get();
		donVi.setDonViChaId(donViInput.getDonViChaId());
		donVi.setTenDonVi(donViInput.getTenDonVi());
		donVi.setTenVietTat(donViInput.getTenVietTat());
		donVi.setSoDienThoai(donViInput.getSoDienThoai());
		donVi.setDiaChi(donViInput.getDiaChi());
		donVi.setFax(donViInput.getFax());
		donVi.setEmail(donViInput.getEmail());
		donVi.setLoaiDonViId(donViInput.getLoaiDonViId());
		donVi.setCapDonViId(donViInput.getCapDonViId());
		//donVi.setAppCode(appCode);
		donVi = donViService.save(donVi);
		return donVi;
	}

	public DmDonVi delete(Long id) throws EntityNotFoundException {
		Optional<DmDonVi> optional = donViService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(DmDonVi.class, "id", String.valueOf(id));
		}
		DmDonVi donVi = optional.get();
		donVi.setDaXoa(true);
		donVi = donViService.save(donVi);
		return donVi;
	}

	public List<DmDonVi> select() {

		List<DmDonVi> list = donViService.listCha();
		List<DmDonVi> listNull = new ArrayList<>();
		List<DmDonVi> listCha = donViService.listCha(list, listNull, "");

		return listCha;
	}

	public List<DmDonVi> getListDonViInUseLimit20(String search, String tenDonVi, String diaChi, String soDienThoai,
			String email, Long donViChaId, Long capId, Long loaiDonViId) {
		Page<DmDonVi> pageDonVi = donViService.findAll(search, tenDonVi, diaChi, soDienThoai, email, donViChaId,
				capId, loaiDonViId, PageRequest.of(0, 20, Direction.ASC, "tenDonVi"));
		List<DmDonVi> listDonVi = pageDonVi.getContent();
		return listDonVi;
	}

	public List<DmDonVi> getListDonViInUseLimit20AndAppCode(String appCode, String search, String tenDonVi, String diaChi,
			String soDienThoai, String email, Long donViChaId, Long capId, Long loaiDonViId) {
		Page<DmDonVi> pageDonVi = donViService.findAll(search, tenDonVi, diaChi, soDienThoai, email, donViChaId,
				capId, loaiDonViId, PageRequest.of(0, 20, Direction.ASC, "tenDonVi"));
		List<DmDonVi> listDonVi = pageDonVi.getContent();
		return listDonVi;
	}
}
