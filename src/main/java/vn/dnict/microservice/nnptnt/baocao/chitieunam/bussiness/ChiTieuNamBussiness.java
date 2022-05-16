package vn.dnict.microservice.nnptnt.baocao.chitieunam.bussiness;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.baocao.chitieu.dao.model.ChiTieu;
import vn.dnict.microservice.nnptnt.baocao.chitieu.dao.service.ChiTieuService;
import vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.model.ChiTieuNam;
import vn.dnict.microservice.nnptnt.baocao.chitieunam.dao.service.ChiTieuNamService;
import vn.dnict.microservice.nnptnt.baocao.data.ChiTieuData;
import vn.dnict.microservice.nnptnt.baocao.data.ChiTieuNamData;
import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.model.DmLinhVuc;
import vn.dnict.microservice.nnptnt.dm.linhvuc.dao.service.DmLinhVucService;

@Service
public class ChiTieuNamBussiness {

	@Autowired
	ChiTieuNamService serviceChiTieuNamService;

	@Autowired
	ChiTieuService serviceChiTieuService;
	
	@Autowired
	DmLinhVucService serviceDmLinhVucService;

	public Page<ChiTieuNamData> findAll(int page, int size, String sortBy, String sortDir, Long linhVucId, Integer nam) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<ChiTieuNam> pageChiTieuNam = serviceChiTieuNamService.findAll(linhVucId, nam,
				PageRequest.of(page, size, direction, sortBy));
		final Page<ChiTieuNamData> pageChiTieuNamData = pageChiTieuNam
				.map(this::convertToChiTieuNamData);
		
		return pageChiTieuNamData;
	}
	
	private ChiTieuNamData convertToChiTieuNamData(ChiTieuNam chiTieuNam) {
		ChiTieuNamData chiTieuNamData = new ChiTieuNamData();
		chiTieuNamData.setId(chiTieuNam.getId());
		chiTieuNamData.setLinhVucId(chiTieuNam.getLinhVucId());
		chiTieuNamData.setNam(chiTieuNam.getNam());
		chiTieuNamData.setTrangThai(chiTieuNam.getTrangThai());
		if(Objects.nonNull(chiTieuNam.getLinhVucId())){
			Optional<DmLinhVuc> optLinhVuc = serviceDmLinhVucService.findById(chiTieuNam.getLinhVucId());
			if(optLinhVuc.isPresent()) {
				chiTieuNamData.setLinhVucId(optLinhVuc.get().getId());
				chiTieuNamData.setLinhVucTen(optLinhVuc.get().getTen());
			}
		}
		return chiTieuNamData;
	}
	
	private ChiTieuNamData convertToChiTieuNamDataAndChiTieuData(ChiTieuNam chiTieuNam) {
		ChiTieuNamData chiTieuNamData = new ChiTieuNamData();
		chiTieuNamData.setId(chiTieuNam.getId());
		chiTieuNamData.setLinhVucId(chiTieuNam.getLinhVucId());
		chiTieuNamData.setNam(chiTieuNam.getNam());
		chiTieuNamData.setTrangThai(chiTieuNam.getTrangThai());
		if(Objects.nonNull(chiTieuNam.getLinhVucId())){
			Optional<DmLinhVuc> optLinhVuc = serviceDmLinhVucService.findById(chiTieuNam.getLinhVucId());
			if(optLinhVuc.isPresent()) {
				chiTieuNamData.setLinhVucId(optLinhVuc.get().getId());
				chiTieuNamData.setLinhVucTen(optLinhVuc.get().getTen());
			}
		}
		List<ChiTieu> chiTieuList = serviceChiTieuService.getByChiTieuNamIdAndChaIdIsNullAndDaXoa(chiTieuNam.getId(),
				false);
		if (Objects.nonNull(chiTieuList) && !chiTieuList.isEmpty()) {
			chiTieuNamData.setChiTieuDatas(setChiTieuDatas(chiTieuList, chiTieuNam.getId()));
		}

		return chiTieuNamData;
	}

	private List<ChiTieuData> setChiTieuDatas(List<ChiTieu> chiTieuList, Long ChiTieuNamId) {
		List<ChiTieuData> chiTieuDatas = new ArrayList<ChiTieuData>();
		for (ChiTieu chiTieu : chiTieuList) {
			ChiTieuData chiTieuData = new ChiTieuData();
			chiTieuData.setId(chiTieu.getId());
			chiTieuData.setChaId(chiTieu.getChaId());
			chiTieuData.setDonViTinh(chiTieu.getDonViTinh());
			chiTieuData.setIsInDam(chiTieu.getIsInDam());
			chiTieuData.setIsInNghieng(chiTieu.getIsInNghieng());
			chiTieuData.setIsNhapGiaTri(chiTieu.getIsNhapGiaTri());
			chiTieuData.setKieuDanhSo(chiTieu.getKieuDanhSo());
			chiTieuData.setSapXep(chiTieu.getSapXep());
			chiTieuData.setTen(chiTieu.getTen());
			chiTieuData.setTrangThai(chiTieu.getTrangThai());
			chiTieuData.setChiTieuNamId(chiTieu.getChiTieuNamId());
			chiTieuData.setIsTinhTong(chiTieu.getIsTinhTong());

			List<ChiTieu> chiTieuListChildren = serviceChiTieuService.getByChiTieuNamIdAndChaIdAndDaXoa(ChiTieuNamId,
					chiTieu.getId(), false);

			List<ChiTieuData> children = new ArrayList<ChiTieuData>();
			if (Objects.nonNull(chiTieuListChildren) && !chiTieuListChildren.isEmpty()) {
				children = setChiTieuDatas(chiTieuListChildren, ChiTieuNamId);
			}
			chiTieuData.setChildren(children);
			chiTieuDatas.add(chiTieuData);
		}

		return chiTieuDatas;

	}

	public ChiTieuNamData findById(Long id) throws EntityNotFoundException {
		Optional<ChiTieuNam> optional = serviceChiTieuNamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ChiTieuNam.class, "id", String.valueOf(id));
		}
		ChiTieuNam chiTieuNam = optional.get();
		return this.convertToChiTieuNamDataAndChiTieuData(chiTieuNam);
	}

	public ChiTieuNamData create(ChiTieuNamData chiTieuNamData) {

		ChiTieuNam chiTieuNam = new ChiTieuNam();
		chiTieuNam.setDaXoa(false);
		chiTieuNam.setLinhVucId(chiTieuNamData.getLinhVucId());
	
		
		chiTieuNam.setNam(chiTieuNamData.getNam());
		chiTieuNam.setTrangThai(chiTieuNamData.getTrangThai());

		chiTieuNam = serviceChiTieuNamService.save(chiTieuNam);
		
		serviceChiTieuService.setFixedDaXoaForChiTieuNamId(false, chiTieuNam.getId());
		List<ChiTieuData> chiTieuDatas = chiTieuNamData.getChiTieuDatas();
		if (Objects.nonNull(chiTieuDatas) && !chiTieuDatas.isEmpty()) {
			saveChiTieuDatas(chiTieuDatas, chiTieuNam, null);
		}

		return this.convertToChiTieuNamDataAndChiTieuData(chiTieuNam);

	}
	
	public ChiTieuNamData update(Long id, ChiTieuNamData chiTieuNamData) 
		throws EntityNotFoundException {
			Optional<ChiTieuNam> optionalChiTieuNam = serviceChiTieuNamService.findById(id);
			if (!optionalChiTieuNam.isPresent()) {
				throw new EntityNotFoundException(ChiTieuNam.class, "id", String.valueOf(id));
			}
			ChiTieuNam chiTieuNam = optionalChiTieuNam.get();
			chiTieuNam.setDaXoa(false);
			chiTieuNam.setLinhVucId(chiTieuNamData.getLinhVucId());
			chiTieuNam.setNam(chiTieuNamData.getNam());
			chiTieuNam.setTrangThai(chiTieuNam.getTrangThai());
			
			chiTieuNam = serviceChiTieuNamService.save(chiTieuNam);
			
			serviceChiTieuService.setFixedDaXoaForChiTieuNamId(false, chiTieuNam.getId());
			List<ChiTieuData> chiTieuDatas = chiTieuNamData.getChiTieuDatas();
			if (Objects.nonNull(chiTieuDatas) && !chiTieuDatas.isEmpty()) {
				saveChiTieuDatas(chiTieuDatas, chiTieuNam, null);
			}

			return this.convertToChiTieuNamDataAndChiTieuData(chiTieuNam);
	}

	private void saveChiTieuDatas(List<ChiTieuData> chiTieuDatas, ChiTieuNam chiTieuNam, Long chaId) {
		int sapXep = 0;
		for (ChiTieuData chiTieuData : chiTieuDatas) {
			sapXep++;
			ChiTieu chiTieu = new ChiTieu();
			if (Objects.nonNull(chiTieuData.getId())) {
				Optional<ChiTieu> optionalChiTieu = serviceChiTieuService.findById(chiTieuData.getId());
				if (optionalChiTieu.isPresent()) {
					chiTieu = optionalChiTieu.get();
				}
				
			}
			System.out.println("+++++++++++++++++++++");
			chiTieu.setDaXoa(false);
			chiTieu.setChaId(chaId);
			chiTieu.setSapXep(sapXep);
			chiTieu.setChiTieuNamId(chiTieuNam.getId());
			chiTieu.setTen(chiTieuData.getTen());
			chiTieu.setTrangThai(chiTieuData.getTrangThai());
			chiTieu.setDonViTinh(chiTieuData.getDonViTinh());
			chiTieu.setIsInDam(chiTieuData.getIsInDam());
			chiTieu.setIsInNghieng(chiTieuData.getIsInNghieng());
			chiTieu.setIsNhapGiaTri(chiTieuData.getIsNhapGiaTri());
			chiTieu.setIsTinhTong(chiTieuData.getIsTinhTong());
			chiTieu.setKieuDanhSo(chiTieuData.getKieuDanhSo());		
			
			chiTieu = serviceChiTieuService.save(chiTieu);
			List<ChiTieuData> children = chiTieuData.getChildren();
			if (Objects.nonNull(children) && !children.isEmpty()) {
				saveChiTieuDatas(children, chiTieuNam, chiTieu.getId());
			}
		}
	
	}
	public ChiTieuNamData findChiTieu(long linhVucId, Integer nam) throws EntityNotFoundException {
		ChiTieuNam chiTieuNam= new ChiTieuNam();
		Optional<ChiTieuNam> optChiTieuNam = serviceChiTieuNamService.findByLinhVucIdAndNamAndDaXoa(linhVucId, nam);
		if(optChiTieuNam.isPresent()) {
			chiTieuNam = optChiTieuNam.get();
		}
		return this.convertToChiTieuNamDataAndChiTieuData(chiTieuNam);
		
	}
	
	public ChiTieuNam delete(Long id) throws EntityNotFoundException {
		Optional<ChiTieuNam> optional = serviceChiTieuNamService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ChiTieuNam.class, "id", String.valueOf(id));
		}
		ChiTieuNam chiTieuNam = optional.get();
		chiTieuNam.setDaXoa(true);
		chiTieuNam = serviceChiTieuNamService.save(chiTieuNam);
		return chiTieuNam;
	}

}
