package vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.danhmuc.dao.service.DmPhuongXaService;
import vn.dnict.microservice.danhmuc.dao.service.DmQuanHuyenService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.model.DmLoaiVatNuoi;
import vn.dnict.microservice.nnptnt.dm.loaivatnuoi.dao.service.DmLoaiVatNuoiService;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.model.CoSoGietMo;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.cosogietmo.dao.service.CoSoGietMoService;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.danhmuc.loaigiayto.dao.model.DmLoaiGiayTo;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.danhmuc.loaigiayto.dao.service.DmLoaiGiayToService;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.data.SoLuongGietMoData;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.data.ThongTinGietMoData;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.data.ThongTinSoLuongGietMoData;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.soluonggietmo.dao.model.SoLuongGietMo;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.soluonggietmo.dao.service.SoLuongGietMoService;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.dao.model.ThongTinGietMo;
import vn.dnict.microservice.nnptnt.kiemsoatgietmo.thongtingietmo.dao.service.ThongTinGietMoService;
import vn.dnict.microservice.nnptnt.vatnuoi.hoatdongchannuoi.dao.model.HoatDongChanNuoi;

@Service
public class ThongTinGietMoBusiness {
	@Autowired
	ThongTinGietMoService serviceThongTinGietMoService;
	
	@Autowired
	DmQuanHuyenService serviceDmQuanHuyenService;
	
	@Autowired
	DmPhuongXaService serviceDmPhuongXaService;
	
	@Autowired
	DmLoaiGiayToService serviceDmLoaiGiayToService;
	
	@Autowired
	DmLoaiVatNuoiService serviceDmLoaiVatNuoiService;
	
	@Autowired
	CoSoGietMoService serviceCoSoGietMoService;
	
	@Autowired
	SoLuongGietMoService serviceSoLuongGietMoService;
	
	public Page<ThongTinGietMoData> findAll(int page, int size, String sortBy, String sortDir, List<String> tenCoSos, String tenChuCoSo,
			String dienThoai, LocalDate gietMoTuNgay, LocalDate gietMoDenNgay, Long quanHuyenId, Long phuongXaId) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<ThongTinGietMo> pageThongTinGietMo = serviceThongTinGietMoService.findAll(tenCoSos, tenChuCoSo, dienThoai, gietMoTuNgay,
				gietMoDenNgay, quanHuyenId, phuongXaId, PageRequest.of(page, size, direction, sortBy));
		final Page<ThongTinGietMoData> pageThongTinGietMoData = pageThongTinGietMo.map(this::convertToThongTinGietMoData);
		return pageThongTinGietMoData;
	}
	
	public ThongTinGietMoData convertToThongTinGietMoData(ThongTinGietMo thongTinGietMo) {
		ThongTinGietMoData thongTinGietMoData = new ThongTinGietMoData();
		thongTinGietMoData.setId(thongTinGietMo.getId());
		thongTinGietMoData.setCoSoGietMoId(thongTinGietMo.getCoSoGietMoId());
		if(thongTinGietMo.getCoSoGietMoId() != null && thongTinGietMo.getCoSoGietMoId() > 0) {
			Optional<CoSoGietMo> optCoSo = serviceCoSoGietMoService.findById(thongTinGietMo.getCoSoGietMoId());
			if(optCoSo.isPresent()) {
				thongTinGietMoData.setCoSoGietMoTen(optCoSo.get().getTenCoSo());
				thongTinGietMoData.setCoSoTenChuCoSo(optCoSo.get().getTenChuCoSo());
				thongTinGietMoData.setCoSoDiaChi(optCoSo.get().getDiaChi());
				thongTinGietMoData.setCoSoDienThoai(optCoSo.get().getDienThoai());
				thongTinGietMoData.setCoSoEmail(optCoSo.get().getEmail());
				thongTinGietMoData.setCoSoQuanHuyenId(optCoSo.get().getQuanHuyenId());
				if(optCoSo.get().getQuanHuyenId() != null && optCoSo.get().getQuanHuyenId() > 0) {
					Optional<DmQuanHuyen> optQuanHuyen = serviceDmQuanHuyenService.findById(optCoSo.get().getQuanHuyenId());
					if(optQuanHuyen.isPresent()) {
						thongTinGietMoData.setCoSoQuanHuyenTen(optQuanHuyen.get().getTen());
					}
				}
				thongTinGietMoData.setCoSoPhuongXaId(optCoSo.get().getPhuongXaId());
				if(optCoSo.get().getPhuongXaId() != null && optCoSo.get().getPhuongXaId() > 0) {
					Optional<DmPhuongXa> optPhuongXa = serviceDmPhuongXaService.findById(optCoSo.get().getPhuongXaId());
					if(optPhuongXa.isPresent()) {
						thongTinGietMoData.setCoSoPhuongXaTen(optPhuongXa.get().getTen());
					}
				}
				thongTinGietMoData.setCoSoGiayKinhDoanh(optCoSo.get().getGiayKinhDoanh());
			}
		}
		
		List<ThongTinSoLuongGietMoData> listThongTinGietMos = new ArrayList<ThongTinSoLuongGietMoData>();
		List<ThongTinGietMo> listThongTins = serviceThongTinGietMoService.findByCoSoGietMoIdAndDaXoa(thongTinGietMo.getCoSoGietMoId(), false);
		if(Objects.nonNull(listThongTins) && !listThongTins.isEmpty()) {
			for(ThongTinGietMo listThongTin : listThongTins) {
				ThongTinSoLuongGietMoData thongTinSoLuongData = new ThongTinSoLuongGietMoData();
				thongTinSoLuongData.setId(listThongTin.getId());
				thongTinSoLuongData.setNgayThang(listThongTin.getNgayThang());
				thongTinSoLuongData.setChuHang(listThongTin.getChuHang());
				thongTinSoLuongData.setSoGiayTo(listThongTin.getSoGiayTo());
				thongTinSoLuongData.setLoaiGiayToId(listThongTin.getLoaiGiayToId());
				if(listThongTin.getLoaiGiayToId() != null && listThongTin.getLoaiGiayToId() > 0) {
					Optional<DmLoaiGiayTo> optGiayTo = serviceDmLoaiGiayToService.findById(listThongTin.getLoaiGiayToId());
					if(optGiayTo.isPresent()) {
						thongTinSoLuongData.setLoaiGiayToTen(optGiayTo.get().getTen());
					}
				}
				thongTinSoLuongData.setCapNgay(listThongTin.getCapNgay());
				
				List<SoLuongGietMoData> listSoLuongGietMos = new ArrayList<SoLuongGietMoData>();
				List<SoLuongGietMo> listGietMos = serviceSoLuongGietMoService.findByThongTinGietMoIdAndDaXoa(thongTinGietMo.getId(), false);
				if(Objects.nonNull(listGietMos) && !listGietMos.isEmpty()) {
					for(SoLuongGietMo soLuong : listGietMos) {
						SoLuongGietMoData soLuongData = new SoLuongGietMoData();
						soLuongData.setId(soLuong.getId());
						soLuongData.setThongTinGietMoId(soLuong.getThongTinGietMoId());
						soLuongData.setNguonGoc(soLuong.getNguonGoc());
						soLuongData.setLoaiVatNuoiId(soLuong.getLoaiVatNuoiId());
						if(soLuong.getLoaiVatNuoiId() != null && soLuong.getLoaiVatNuoiId() > 0) {
							Optional<DmLoaiVatNuoi> optVatNuoi = serviceDmLoaiVatNuoiService.findById(soLuong.getLoaiVatNuoiId());
							if(optVatNuoi.isPresent()) {
								soLuongData.setLoaiVatNuoiTen(optVatNuoi.get().getTen());
							}
						}
						soLuongData.setSoLuongGietMo(soLuong.getSoLuongGietMo());
						soLuongData.setGhiChu(soLuong.getGhiChu());
						
						listSoLuongGietMos.add(soLuongData);
					}
				}
				thongTinSoLuongData.setListSoLuongGietMo(listSoLuongGietMos);
				listThongTinGietMos.add(thongTinSoLuongData);
			}			
		}
		thongTinGietMoData.setListThongTinGietMo(listThongTinGietMos);
		return thongTinGietMoData;
	}
	
	public ThongTinGietMoData findById(Long id) throws EntityNotFoundException {
		Optional<ThongTinGietMo> optional = serviceThongTinGietMoService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinGietMoData.class, "id", String.valueOf(id));
		}
		ThongTinGietMo thongTinGietMo = optional.get();
		return this.convertToThongTinGietMoData(thongTinGietMo);
	}
	
	public List<ThongTinGietMo> getThongTinGietMoByCoSoAndNgayThangAndChuHangAndLoaiGiayToAndSoGiayTo(Long coSoGietMoId, LocalDate ngayThang,
			String chuHang, Long loaiGiayToId, String soGiayTo) throws EntityNotFoundException {
		List<ThongTinGietMo> list = serviceThongTinGietMoService
				.findByCoSoGietMoIdAndNgayThangAndChuHangAndLoaiGiayToIdAndSoGiayToAndDaXoa(coSoGietMoId, ngayThang, chuHang, loaiGiayToId,
						soGiayTo, false);
		return list;
	}
	
	public List<ThongTinGietMo> create(ThongTinGietMoData thongTinGietMoData, BindingResult result) throws MethodArgumentNotValidException {
		if (result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		List<ThongTinGietMo> list = new ArrayList<ThongTinGietMo>();
		if(thongTinGietMoData.getListThongTinGietMo().size() > 0) {
			for(int i = 0; i < thongTinGietMoData.getListThongTinGietMo().size(); i++) {
				ThongTinGietMo thongTinGietMo = new ThongTinGietMo();
				if(thongTinGietMoData.getListThongTinGietMo().get(i).getId() != null &&
						thongTinGietMoData.getListThongTinGietMo().get(i).getId() > 0) {
					Optional<ThongTinGietMo> optTtGm = serviceThongTinGietMoService
							.findById(thongTinGietMoData.getListThongTinGietMo().get(i).getId());
					if(optTtGm.isPresent()) {
						thongTinGietMo = optTtGm.get();
					}
				}
				thongTinGietMo.setNgayThang(thongTinGietMoData.getListThongTinGietMo().get(i).getNgayThang());
				thongTinGietMo.setChuHang(thongTinGietMoData.getListThongTinGietMo().get(i).getChuHang());
				thongTinGietMo.setSoGiayTo(thongTinGietMoData.getListThongTinGietMo().get(i).getSoGiayTo());
				thongTinGietMo.setLoaiGiayToId(thongTinGietMoData.getListThongTinGietMo().get(i).getLoaiGiayToId());
				thongTinGietMo.setCapNgay(thongTinGietMoData.getListThongTinGietMo().get(i).getCapNgay());
				thongTinGietMo.setCoSoGietMoId(thongTinGietMoData.getCoSoGietMoId());
				thongTinGietMo = serviceThongTinGietMoService.save(thongTinGietMo);
				
				serviceSoLuongGietMoService.setFixedDaXoaAndThongTinGietMoId(false, thongTinGietMo.getId());
				List<SoLuongGietMoData> listSoLuongGietMos = thongTinGietMoData.getListThongTinGietMo().get(i).getListSoLuongGietMo();
				if(Objects.nonNull(listSoLuongGietMos) && !listSoLuongGietMos.isEmpty()) {
					for(SoLuongGietMoData soLuongGietMoData : listSoLuongGietMos) {
						SoLuongGietMo soLuongGietMo = new SoLuongGietMo();
						if(Objects.nonNull(soLuongGietMo.getId())) {
							Optional<SoLuongGietMo> optSoLuong = serviceSoLuongGietMoService.findById(soLuongGietMo.getId());
							if(optSoLuong.isPresent()) {
								soLuongGietMo = optSoLuong.get();
							}
						}
						soLuongGietMo.setId(soLuongGietMoData.getId());
						soLuongGietMo.setThongTinGietMoId(soLuongGietMoData.getThongTinGietMoId());
						soLuongGietMo.setNguonGoc(soLuongGietMoData.getNguonGoc());
						soLuongGietMo.setLoaiVatNuoiId(soLuongGietMoData.getLoaiVatNuoiId());
						soLuongGietMo.setSoLuongGietMo(soLuongGietMoData.getSoLuongGietMo());
						soLuongGietMo.setGhiChu(soLuongGietMoData.getGhiChu());
						serviceSoLuongGietMoService.save(soLuongGietMo);
					}
				}
				list.add(thongTinGietMo);
			}
		}
		return list;
	}
	
	@DeleteMapping(value = { "/{id}" })
	public ThongTinGietMoData delete(Long id) throws EntityNotFoundException {
		Optional<ThongTinGietMo> optional = serviceThongTinGietMoService.findById(id);
		if(!optional.isPresent()) {
			throw new EntityNotFoundException(HoatDongChanNuoi.class, "id", String.valueOf(id));
		}
		ThongTinGietMo thongTinGietMo = optional.get();
		thongTinGietMo.setDaXoa(true);
		thongTinGietMo = serviceThongTinGietMoService.save(thongTinGietMo);
		return this.convertToThongTinGietMoData(thongTinGietMo);
	}
}
