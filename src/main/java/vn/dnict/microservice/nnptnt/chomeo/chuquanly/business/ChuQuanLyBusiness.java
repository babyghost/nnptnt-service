package vn.dnict.microservice.nnptnt.chomeo.chuquanly.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.core.dao.model.CoreChucNang;
import vn.dnict.microservice.core.data.CoreChucNangData;
import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.danhmuc.dao.service.DmPhuongXaService;
import vn.dnict.microservice.danhmuc.dao.service.DmQuanHuyenService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.service.ChuQuanLyService;
import vn.dnict.microservice.nnptnt.chomeo.data.ChuQuanLyData;
import vn.dnict.microservice.nnptnt.chomeo.data.ThongTinChoMeoData;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.model.KeHoach2ChoMeo;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.service.KeHoach2ChoMeoService;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model.ThongTinChoMeo;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.service.ThongTinChoMeoService;
import vn.dnict.microservice.nnptnt.dm.giong.dao.model.DmGiong;
import vn.dnict.microservice.nnptnt.dm.giong.dao.service.DmGiongService;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.model.DmLoaiDongVat;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.service.DmLoaiDongVatService;

@Service
public class ChuQuanLyBusiness {
	@Autowired
	ChuQuanLyService serviceChuQuanLyService;

	@Autowired
	DmQuanHuyenService serviceDmQuanHuyenService;

	@Autowired
	DmPhuongXaService serviceDmPhuongXaService;

	@Autowired
	ThongTinChoMeoService serviceThongTinChoMeoService;

	@Autowired
	KeHoach2ChoMeoService serviceKeHoach2ChoMeoService;
	@Autowired
	DmGiongService serviceDmGiongService;
	@Autowired
	DmLoaiDongVatService serviceDmLoaiDongVatService;
	public Page<ChuQuanLyData> findAll(int page, int size, String sortBy, String sortDir, String chuHo, String diaChi,
			String dienThoai) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		final Page<ChuQuanLy> pageChuQuanLy = serviceChuQuanLyService.findAll(chuHo, diaChi, dienThoai,
				PageRequest.of(page, size, direction, sortBy));
		final Page<ChuQuanLyData> pageChuQuanLyData = pageChuQuanLy.map(this::convertToChuQuanLyData);
		return pageChuQuanLyData;
	}

//	public ChuQuanLyData findById(Long id) throws EntityNotFoundException {
//		Optional<ChuQuanLy> optional = serviceChuQuanLyService.findById(id);
//		if (!optional.isPresent()) {
//			throw new EntityNotFoundException(ChuQuanLyData.class, "id", String.valueOf(id));
//		}
//		ChuQuanLy chuQuanLy = optional.get();
//		return this.convertToChuQuanLyData(chuQuanLy);
//	}

	public ChuQuanLyData findById(Long id) throws EntityNotFoundException {
		Optional<ChuQuanLy> optional = serviceChuQuanLyService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ChuQuanLyData.class, "id", String.valueOf(id));
		}
		ChuQuanLy chuQuanLy = optional.get();
		ChuQuanLyData chuQuanLyData = new ChuQuanLyData();
		chuQuanLyData.setId(chuQuanLy.getId());
		chuQuanLyData.setChuHo(chuQuanLy.getChuHo());
		chuQuanLyData.setDiaChi(chuQuanLy.getDiaChi());
		chuQuanLyData.setDienThoai(chuQuanLy.getDienThoai());
		chuQuanLyData.setQuanHuyen_Id(chuQuanLy.getQuanHuyen_Id());
		chuQuanLyData.setPhuongXa_Id(chuQuanLy.getPhuongXa_Id());
		if (chuQuanLy.getQuanHuyen_Id() != null && chuQuanLy.getQuanHuyen_Id() > 0) {
			Optional<DmQuanHuyen> optionalQuanHuyen = serviceDmQuanHuyenService.findById(chuQuanLy.getQuanHuyen_Id());
			if (optionalQuanHuyen.isPresent()) {
				chuQuanLyData.setQuanHuyen_Ten(optionalQuanHuyen.get().getTen());
			}
		}
		if (chuQuanLy.getPhuongXa_Id() != null && chuQuanLy.getPhuongXa_Id() > 0) {
			Optional<DmPhuongXa> optionalPhuongXa = serviceDmPhuongXaService.findById(chuQuanLy.getPhuongXa_Id());
			if (optionalPhuongXa.isPresent()) {
				chuQuanLyData.setPhuongXa_Ten(optionalPhuongXa.get().getTen());
			}
		}
		
		List<ThongTinChoMeoData> thongTinChoMeoDatas = new ArrayList<>();
		List<ThongTinChoMeo> thongTinChoMeos = serviceThongTinChoMeoService.findByChuQuanLyIdAndDaXoa(chuQuanLy.getId(),
				false);
		if (Objects.nonNull(thongTinChoMeos) && !thongTinChoMeos.isEmpty()) {
			for (ThongTinChoMeo thongTinChoMeo : thongTinChoMeos) {
				ThongTinChoMeoData thongTinChoMeoData = new ThongTinChoMeoData();
				thongTinChoMeoData.setId(thongTinChoMeo.getId());
				thongTinChoMeoData.setGiongId(thongTinChoMeo.getGiongId());
				thongTinChoMeoData.setLoaiDongVatId(thongTinChoMeo.getLoaiDongVatId());
				thongTinChoMeoData.setMauLong(thongTinChoMeo.getMauLong());
				thongTinChoMeoData.setNamSinh(thongTinChoMeo.getNamSinh());
				thongTinChoMeoData.setTenConVat(thongTinChoMeo.getTenConVat());
				thongTinChoMeoData.setTinhBiet(thongTinChoMeo.getTinhBiet());
				thongTinChoMeoData.setTrangThai(thongTinChoMeo.getTrangThai());
				
				if (thongTinChoMeoData.getGiongId() != null && thongTinChoMeoData.getGiongId() > 0) {
					Optional<DmGiong> optionalGiong = serviceDmGiongService.findById(thongTinChoMeoData.getGiongId());
					if (optionalGiong.isPresent()) {
						
						thongTinChoMeoData.setGiong(optionalGiong.get().getTen());
					}
				}

				if (thongTinChoMeoData.getLoaiDongVatId() != null && thongTinChoMeoData.getLoaiDongVatId() > 0) {
					Optional<DmLoaiDongVat> optionalLoaiDongVat = serviceDmLoaiDongVatService
							.findById(thongTinChoMeoData.getLoaiDongVatId());
					if (optionalLoaiDongVat.isPresent()) {
						
						thongTinChoMeoData.setLoaiDongVat(optionalLoaiDongVat.get().getTen());
						
					}
				}

//				Optional<KeHoach2ChoMeo> optionalKeHoach2 = serviceKeHoach2ChoMeoService.findById(thongTinChoMeoData.getId());
//				if(optionalKeHoach2.isPresent()) {
//					ThongTinChoMeoData.setNgayTiemPhong(optionalKeHoach2.get().getNgayTiemPhong());
//				}
			
				thongTinChoMeoDatas.add(thongTinChoMeoData);
			}
		} else {
			System.out
					.println("lol" + serviceThongTinChoMeoService.findByChuQuanLyIdAndDaXoa(chuQuanLy.getId(), false));
		}
		chuQuanLyData.setThongTinChoMeoDatas(thongTinChoMeoDatas);
		return chuQuanLyData;
	}

	private ChuQuanLyData convertToChuQuanLyData(ChuQuanLy chuQuanLy) {
		ChuQuanLyData chuQuanLyData = new ChuQuanLyData();
		chuQuanLyData.setId(chuQuanLy.getId());
		chuQuanLyData.setChuHo(chuQuanLy.getChuHo());
		chuQuanLyData.setDiaChi(chuQuanLy.getDiaChi());
		chuQuanLyData.setDienThoai(chuQuanLy.getDienThoai());
		chuQuanLyData.setQuanHuyen_Id(chuQuanLy.getQuanHuyen_Id());
		chuQuanLyData.setPhuongXa_Id(chuQuanLy.getPhuongXa_Id());
		if (chuQuanLy.getQuanHuyen_Id() != null && chuQuanLy.getQuanHuyen_Id() > 0) {
			Optional<DmQuanHuyen> optional = serviceDmQuanHuyenService.findById(chuQuanLy.getQuanHuyen_Id());
			if (optional.isPresent()) {
				chuQuanLyData.setQuanHuyen_Ten(optional.get().getTen());
			}
		}
		if (chuQuanLy.getPhuongXa_Id() != null && chuQuanLy.getPhuongXa_Id() > 0) {
			Optional<DmPhuongXa> optional = serviceDmPhuongXaService.findById(chuQuanLy.getPhuongXa_Id());
			if (optional.isPresent()) {
				chuQuanLyData.setPhuongXa_Ten(optional.get().getTen());
			}
		}
		List<ThongTinChoMeoData> thongTinChoMeoDatas = new ArrayList<>();
		List<ThongTinChoMeo> thongTinChoMeos = serviceThongTinChoMeoService.findByChuQuanLyIdAndDaXoa(chuQuanLy.getId(),
				false);
		if (Objects.nonNull(thongTinChoMeos) && !thongTinChoMeos.isEmpty()) {
			for (ThongTinChoMeo thongTinChoMeo : thongTinChoMeos) {
				ThongTinChoMeoData thongTinChoMeoData = new ThongTinChoMeoData();
				thongTinChoMeoData.setId(thongTinChoMeo.getId());
				thongTinChoMeoData.setGiongId(thongTinChoMeo.getGiongId());
				thongTinChoMeoData.setLoaiDongVatId(thongTinChoMeo.getLoaiDongVatId());
				thongTinChoMeoData.setMauLong(thongTinChoMeo.getMauLong());
				thongTinChoMeoData.setNamSinh(thongTinChoMeo.getNamSinh());
				thongTinChoMeoData.setTenConVat(thongTinChoMeo.getTenConVat());
				thongTinChoMeoData.setTinhBiet(thongTinChoMeo.getTinhBiet());
				thongTinChoMeoData.setTrangThai(thongTinChoMeo.getTrangThai());
				if (thongTinChoMeoData.getGiongId() != null && thongTinChoMeoData.getGiongId() > 0) {
					Optional<DmGiong> optionalGiong = serviceDmGiongService.findById(thongTinChoMeoData.getGiongId());
					if (optionalGiong.isPresent()) {
						
						thongTinChoMeoData.setGiong(optionalGiong.get().getTen());
					}
				}

				if (thongTinChoMeoData.getLoaiDongVatId() != null && thongTinChoMeoData.getLoaiDongVatId() > 0) {
					Optional<DmLoaiDongVat> optionalLoaiDongVat = serviceDmLoaiDongVatService
							.findById(thongTinChoMeoData.getLoaiDongVatId());
					if (optionalLoaiDongVat.isPresent()) {
						;
						thongTinChoMeoData.setLoaiDongVat(optionalLoaiDongVat.get().getTen());
						
					}
				}
				thongTinChoMeoDatas.add(thongTinChoMeoData);
			}
		}
		return chuQuanLyData;
	}

	public ChuQuanLy create(ChuQuanLyData chuQuanLyData) {
		ChuQuanLy chuQuanLy = new ChuQuanLy();
		chuQuanLy.setChuHo(chuQuanLyData.getChuHo());
		chuQuanLy.setDiaChi(chuQuanLyData.getDiaChi());
		chuQuanLy.setDienThoai(chuQuanLyData.getDienThoai());
		chuQuanLy.setQuanHuyen_Id(chuQuanLyData.getQuanHuyen_Id());
		chuQuanLy.setPhuongXa_Id(chuQuanLyData.getPhuongXa_Id());
		chuQuanLy.setDaXoa(false);
		chuQuanLy = serviceChuQuanLyService.save(chuQuanLy);

		serviceThongTinChoMeoService.setFixedDaXoaForChuQuanLyId(false, chuQuanLy.getId());
		List<ThongTinChoMeoData> thongTinChoMeoDatas = chuQuanLyData.getThongTinChoMeoDatas();
		if (Objects.nonNull(thongTinChoMeoDatas) && !thongTinChoMeoDatas.isEmpty()) {
			for (ThongTinChoMeoData thongTinChoMeoData : thongTinChoMeoDatas) {
				ThongTinChoMeo thongTinChoMeo = new ThongTinChoMeo();
				if (Objects.nonNull(thongTinChoMeoData.getId())) {
					Optional<ThongTinChoMeo> optionalThongTinChoMeo = serviceThongTinChoMeoService
							.findById(thongTinChoMeoData.getId());
					if (optionalThongTinChoMeo.isPresent()) {
						thongTinChoMeo = optionalThongTinChoMeo.get();
					}
				}

				thongTinChoMeo.setId(thongTinChoMeoData.getId());
				thongTinChoMeo.setChuQuanLyId(chuQuanLy.getId());
				thongTinChoMeo.setDaXoa(false);
				thongTinChoMeo.setGiongId(thongTinChoMeoData.getGiongId());
				thongTinChoMeo.setLoaiDongVatId(thongTinChoMeoData.getLoaiDongVatId());
				thongTinChoMeo.setMauLong(thongTinChoMeoData.getMauLong());
				thongTinChoMeo.setNamSinh(thongTinChoMeoData.getNamSinh());
				thongTinChoMeo.setTenConVat(thongTinChoMeoData.getTenConVat());
				thongTinChoMeo.setTinhBiet(thongTinChoMeoData.getTinhBiet());
				thongTinChoMeo.setTrangThai(thongTinChoMeoData.getTrangThai());
				if (thongTinChoMeoData.getGiongId() != null && thongTinChoMeoData.getGiongId() > 0) {
					Optional<DmGiong> optionalGiong = serviceDmGiongService.findById(thongTinChoMeoData.getGiongId());
					if (optionalGiong.isPresent()) {
						
						thongTinChoMeoData.setGiong(optionalGiong.get().getTen());
					}
				}

				if (thongTinChoMeoData.getLoaiDongVatId() != null && thongTinChoMeoData.getLoaiDongVatId() > 0) {
					Optional<DmLoaiDongVat> optionalLoaiDongVat = serviceDmLoaiDongVatService
							.findById(thongTinChoMeoData.getLoaiDongVatId());
					if (optionalLoaiDongVat.isPresent()) {
						;
						thongTinChoMeoData.setLoaiDongVat(optionalLoaiDongVat.get().getTen());
						
					}
				}
				serviceThongTinChoMeoService.save(thongTinChoMeo);
			}
		}

		return chuQuanLy;
	}

	public ChuQuanLy update(Long id, ChuQuanLyData chuQuanLyData) throws EntityNotFoundException {
		Optional<ChuQuanLy> optional = serviceChuQuanLyService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ChuQuanLy.class, "id", String.valueOf(id));
		}
		ChuQuanLy chuQuanLy = optional.get();
		chuQuanLy.setChuHo(chuQuanLyData.getChuHo());
		chuQuanLy.setDiaChi(chuQuanLyData.getDiaChi());
		chuQuanLy.setDienThoai(chuQuanLyData.getDienThoai());
		chuQuanLy.setQuanHuyen_Id(chuQuanLyData.getQuanHuyen_Id());
		chuQuanLy.setPhuongXa_Id(chuQuanLyData.getPhuongXa_Id());
		chuQuanLy.setDaXoa(false);
		chuQuanLy = serviceChuQuanLyService.save(chuQuanLy);

		serviceThongTinChoMeoService.setFixedDaXoaForChuQuanLyId(false, chuQuanLy.getId());
		List<ThongTinChoMeoData> thongTinChoMeoDatas = chuQuanLyData.getThongTinChoMeoDatas();
		if (Objects.nonNull(thongTinChoMeoDatas) && !thongTinChoMeoDatas.isEmpty()) {
			for (ThongTinChoMeoData thongTinChoMeoData : thongTinChoMeoDatas) {
				ThongTinChoMeo thongTinChoMeo = new ThongTinChoMeo();
				if (Objects.nonNull(thongTinChoMeoData.getId())) {
					Optional<ThongTinChoMeo> optionalThongTinChoMeo = serviceThongTinChoMeoService
							.findById(thongTinChoMeoData.getId());
					if (optionalThongTinChoMeo.isPresent()) {
						thongTinChoMeo = optionalThongTinChoMeo.get();
					}
				}

				thongTinChoMeo.setId(thongTinChoMeoData.getId());
				thongTinChoMeo.setChuQuanLyId(chuQuanLy.getId());
				thongTinChoMeo.setDaXoa(false);
				thongTinChoMeo.setGiongId(thongTinChoMeoData.getGiongId());
				thongTinChoMeo.setLoaiDongVatId(thongTinChoMeoData.getLoaiDongVatId());
				thongTinChoMeo.setMauLong(thongTinChoMeoData.getMauLong());
				thongTinChoMeo.setNamSinh(thongTinChoMeoData.getNamSinh());
				thongTinChoMeo.setTenConVat(thongTinChoMeoData.getTenConVat());
				thongTinChoMeo.setTinhBiet(thongTinChoMeoData.getTinhBiet());
				thongTinChoMeo.setTrangThai(thongTinChoMeoData.getTrangThai());
				if (thongTinChoMeoData.getGiongId() != null && thongTinChoMeoData.getGiongId() > 0) {
					Optional<DmGiong> optionalGiong = serviceDmGiongService.findById(thongTinChoMeoData.getGiongId());
					if (optionalGiong.isPresent()) {
						
						thongTinChoMeoData.setGiong(optionalGiong.get().getTen());
					}
				}

				if (thongTinChoMeoData.getLoaiDongVatId() != null && thongTinChoMeoData.getLoaiDongVatId() > 0) {
					Optional<DmLoaiDongVat> optionalLoaiDongVat = serviceDmLoaiDongVatService
							.findById(thongTinChoMeoData.getLoaiDongVatId());
					if (optionalLoaiDongVat.isPresent()) {
						;
						thongTinChoMeoData.setLoaiDongVat(optionalLoaiDongVat.get().getTen());
						
					}
				}
				serviceThongTinChoMeoService.save(thongTinChoMeo);
			}
		}
		return chuQuanLy;

	}

	@DeleteMapping(value = { "/{id}" })
	public ChuQuanLyData delete(Long id) throws EntityNotFoundException {
		Optional<ChuQuanLy> optional = serviceChuQuanLyService.findById(id);
		if (!optional.isPresent()) {
			System.out.println("X");
			throw new EntityNotFoundException(ChuQuanLy.class, "id", String.valueOf(id));
		}

		ChuQuanLy chuQuanLy = optional.get();
		chuQuanLy.setDaXoa(true);
		System.out.println(chuQuanLy);
		chuQuanLy = serviceChuQuanLyService.save(chuQuanLy);
		return this.convertToChuQuanLyData(chuQuanLy);
	}
}
