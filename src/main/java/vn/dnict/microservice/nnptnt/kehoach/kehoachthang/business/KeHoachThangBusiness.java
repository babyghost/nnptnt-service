package vn.dnict.microservice.nnptnt.kehoach.kehoachthang.business;

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

import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.danhmuc.dao.model.DmCanBo;
import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;
import vn.dnict.microservice.danhmuc.dao.service.DmCanBoService;
import vn.dnict.microservice.danhmuc.dao.service.DmDonViService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoachThangData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuThangData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuThangLogData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVuTongHopThangData;
import vn.dnict.microservice.nnptnt.kehoach.data.TienDoNhiemVuThangData;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.model.KeHoachThang;
import vn.dnict.microservice.nnptnt.kehoach.kehoachthang.dao.service.KeHoachThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.model.NhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang.dao.service.NhiemVuThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthang2filedinhkem.dao.service.FileDinhKemNhiemVuThangService;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.model.NhiemVuThangLog;
import vn.dnict.microservice.nnptnt.kehoach.nhiemvuthanglog.dao.service.NhiemVuThangLogService;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.model.TienDoNhiemVuThang;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvuthang.dao.service.TienDoNhiemVuThangService;
import vn.dnict.microservice.utils.Constants;

@Service
public class KeHoachThangBusiness {
	@Autowired
	KeHoachThangService serviceKeHoachThangService;
	
	@Autowired
	DmDonViService serviceDmDonViService;
	
	@Autowired
	DmCanBoService serviceDmCanBoService;
	
	@Autowired
	NhiemVuThangService serviceNhiemVuThangService;
	
	@Autowired
	CoreAttachmentBusiness coreAttachmentBusiness;
	
	@Autowired
	TienDoNhiemVuThangService serviceTienDoNhiemVuThangService;
	
	@Autowired
	FileDinhKemNhiemVuThangService serviceFileDinhKemNhiemVuThangService;
	
	@Autowired
	NhiemVuThangLogService serviceNhiemVuThangLogService;
	
	public Page<KeHoachThangData> findAll(int page, int size, String sortBy, String sortDir, Long donViChuTriId, Integer thang,
			String tenNhiemVu, String canBoThucHienTen, LocalDate tuThoiHan, LocalDate denThoiHan, Integer tinhTrang) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		final Page<KeHoachThang> pageKeHoachThang = serviceKeHoachThangService.findAll(donViChuTriId, thang, tenNhiemVu, canBoThucHienTen,
				tuThoiHan, denThoiHan, tinhTrang, PageRequest.of(page, size, direction, sortBy));
		final Page<KeHoachThangData> pageKeHoachThangData = pageKeHoachThang
				.map(this::convertToKeHoachThangData);
		return pageKeHoachThangData;
	}

	private KeHoachThangData convertToKeHoachThangData(KeHoachThang keHoachThang) {
		KeHoachThangData keHoachThangData = new KeHoachThangData();
		keHoachThangData.setId(keHoachThang.getId());
		if (Objects.nonNull(keHoachThang.getDonViChuTriId())) {
			Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(keHoachThang.getDonViChuTriId());
			if (optionalDmDonVi.isPresent()) {
				keHoachThangData.setDonViChuTriId(optionalDmDonVi.get().getId());
				keHoachThangData.setDonViChuTriTen(optionalDmDonVi.get().getTenDonVi());
			}
		}
		keHoachThangData.setThang(keHoachThang.getThang());

		List<NhiemVuThang> nhiemVuThangs = serviceNhiemVuThangService
				.findByKeHoachThangIdAndDaXoa(keHoachThang.getId(), false);
		List<NhiemVuThangData> nhiemVuThangDatas = new ArrayList<NhiemVuThangData>();
		List<NhiemVuThangData> nhiemVuThangTruocDatas = new ArrayList<NhiemVuThangData>();
		if (Objects.nonNull(nhiemVuThangDatas) && !nhiemVuThangDatas.isEmpty()) {
			for (NhiemVuThang nhiemVuThang : nhiemVuThangs) {
				NhiemVuThangData nhiemVuThangData = new NhiemVuThangData();
				nhiemVuThangData.setId(nhiemVuThang.getId());
				nhiemVuThangData.setTenNhiemVu(nhiemVuThang.getTenNhiemVu());
				nhiemVuThangData.setDonViPhoiHop(nhiemVuThang.getDonViPhoiHop());
				nhiemVuThangData.setIsNhiemVuThangTruoc(nhiemVuThang.getIsNhiemVuThangTruoc());
				nhiemVuThangData.setKeHoachThangId(nhiemVuThang.getKeHoachThangId());
				nhiemVuThangData.setNhiemVuThangTruocId(nhiemVuThang.getNhiemVuThangTruocId());
				nhiemVuThangData.setThoiGian(nhiemVuThang.getThoiGian());
				nhiemVuThangData.setGhiChu(nhiemVuThang.getGhiChu());
				nhiemVuThangData.setTinhTrang(nhiemVuThang.getTinhTrang());
				if (Objects.nonNull(nhiemVuThang.getCanBoThucHienId())) {
					Optional<DmCanBo> optionalDmCanBo = serviceDmCanBoService.findById(nhiemVuThang.getCanBoThucHienId());
					if (optionalDmCanBo.isPresent()) {
						nhiemVuThangData.setCanBoThucHienId(optionalDmCanBo.get().getId());
						nhiemVuThangData.setCanBoThucHienTen(optionalDmCanBo.get().getHoTen());
					}
				}
				if (Objects.nonNull(nhiemVuThang.getIsNhiemVuThangTruoc()) && nhiemVuThang.getIsNhiemVuThangTruoc() == false) {
					nhiemVuThangTruocDatas.add(nhiemVuThangData);
				} else {
					nhiemVuThangTruocDatas.add(nhiemVuThangData);
				}

				// tiến độ
				TienDoNhiemVuThang tienDoNhiemVuThang = new TienDoNhiemVuThang();
				if (Objects.nonNull(nhiemVuThang.getTienDoNhiemVuId())) {
					Optional<TienDoNhiemVuThang> optionalTienDoNhiemVuThang = serviceTienDoNhiemVuThangService
							.findById(nhiemVuThang.getTienDoNhiemVuId());
					if (optionalTienDoNhiemVuThang.isPresent()) {
						tienDoNhiemVuThang = optionalTienDoNhiemVuThang.get();
					}
				}
				TienDoNhiemVuThangData tienDoNhiemVuThangData = new TienDoNhiemVuThangData();
				tienDoNhiemVuThangData.setId(tienDoNhiemVuThang.getId());
				tienDoNhiemVuThangData.setKetQua(tienDoNhiemVuThang.getKetQua());
				tienDoNhiemVuThangData.setMucDoHoanThanh(tienDoNhiemVuThang.getMucDoHoanThanh());
				tienDoNhiemVuThangData.setTenNguoiCapNhat(tienDoNhiemVuThang.getTenNguoiCapNhat());
				tienDoNhiemVuThangData.setNgayCapNhat(LocalDate.now());
				nhiemVuThangData.setTienDoNhiemVuThangData(tienDoNhiemVuThangData);

				// log
				List<NhiemVuThangLog> nhiemVuThangLogs = serviceNhiemVuThangLogService.findByNhiemVuThangId(nhiemVuThang.getId());
				List<NhiemVuThangLogData> nhiemVuThangLogDatas = new ArrayList<NhiemVuThangLogData>();
				if (Objects.nonNull(nhiemVuThangLogs) && !nhiemVuThangLogs.isEmpty()) {
					for (NhiemVuThangLog nhiemVuThangLog : nhiemVuThangLogs) {
						NhiemVuThangLogData nhiemVuThangLogData = new NhiemVuThangLogData();
						nhiemVuThangLogData.setId(nhiemVuThangLog.getId());
						nhiemVuThangLogData.setKetQua(nhiemVuThangLog.getKetQua());
						nhiemVuThangLogData.setMucDoHoanThanh(nhiemVuThangLog.getMucDoHoanThanh());
						nhiemVuThangLogData.setNhiemVuThangId(nhiemVuThangLog.getNhiemVuThangId());
						nhiemVuThangLogData.setTenNguoiCapNhat(nhiemVuThangLog.getTenNguoiCapNhat());
						nhiemVuThangLogData.setTenNhiemVu(nhiemVuThangLog.getTenNhiemVu());
						nhiemVuThangLogData.setTinhTrang(nhiemVuThangLog.getTinhTrang());
						nhiemVuThangLogData.setNgayCapNhat(nhiemVuThangLog.getNgayCapNhat().toLocalDate());
						if (Objects.nonNull(nhiemVuThangLog.getCanBoThucHienId())) {
							Optional<DmCanBo> optionalDmCanBo = serviceDmCanBoService
									.findById(nhiemVuThangLog.getCanBoThucHienId());
							if (optionalDmCanBo.isPresent()) {
								nhiemVuThangLogData.setCanBoThucHienId(optionalDmCanBo.get().getId());
								nhiemVuThangLogData.setCanBoThucHienTen(optionalDmCanBo.get().getHoTen());
							}
						}
						nhiemVuThangLogDatas.add(nhiemVuThangLogData);
					}
				}
				nhiemVuThangData.setNhiemVuThangLogDatas(nhiemVuThangLogDatas);
			}
		}
		List<NhiemVuTongHopThangData> nhiemVuTongHopThangDatas = new ArrayList<NhiemVuTongHopThangData>();
		NhiemVuTongHopThangData nhiemVuTongHopThangData = new NhiemVuTongHopThangData();
		nhiemVuTongHopThangData.setStt("I");
		nhiemVuTongHopThangData.setTen("Nhiệm vụ trong tháng");
		nhiemVuTongHopThangData.setNhiemVuThangDatas(nhiemVuThangDatas);
		nhiemVuTongHopThangDatas.add(nhiemVuTongHopThangData);
		NhiemVuTongHopThangData nhiemVuTongHopThangTruocData = new NhiemVuTongHopThangData();
		nhiemVuTongHopThangTruocData.setStt("II");
		nhiemVuTongHopThangTruocData.setTen("Nhiệm vụ tháng trước chuyển sang");
		nhiemVuTongHopThangTruocData.setNhiemVuThangDatas(nhiemVuThangTruocDatas);
		nhiemVuTongHopThangDatas.add(nhiemVuTongHopThangTruocData);

		keHoachThangData.setNhiemVuTongHopThangDatas(nhiemVuTongHopThangDatas);
		return keHoachThangData;
	}

	public KeHoachThangData findById(Long id) throws EntityNotFoundException {
		Optional<KeHoachThang> optional = serviceKeHoachThangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachThang.class, "id", String.valueOf(id));
		}
		KeHoachThang keHoachThang = optional.get();
		return this.convertToKeHoachThangData(keHoachThang);
	}

	public KeHoachThangData findByDonViChuTriIdAndThang(Long donViChuTriId, LocalDate thang) {
		KeHoachThang keHoachThang = new KeHoachThang();
		List<KeHoachThang> keHoachThangs = serviceKeHoachThangService.findByDonViChuTriIdAndThangAndDaXoa(donViChuTriId, thang, false);
		if (Objects.nonNull(keHoachThangs) && !keHoachThangs.isEmpty()) {
			keHoachThang = keHoachThangs.get(0);
		}
		keHoachThang.setDonViChuTriId(donViChuTriId);
		keHoachThang.setThang(thang);
		return this.convertToKeHoachThangData(keHoachThang);
	}

	public KeHoachThangData save(KeHoachThangData keHoachThangData) throws EntityNotFoundException {

		KeHoachThang keHoachThang = new KeHoachThang();
		if (Objects.nonNull(keHoachThangData.getId())) {
			Optional<KeHoachThang> optionalKeHoachThang = serviceKeHoachThangService.findById(keHoachThangData.getId());
			if (optionalKeHoachThang.isPresent()) {
				keHoachThang = optionalKeHoachThang.get();
			}
		}
		keHoachThang.setDaXoa(false);
		if (Objects.nonNull(keHoachThangData.getDonViChuTriId())) {
			Optional<DmDonVi> optionalDmDonVi = serviceDmDonViService.findById(keHoachThangData.getDonViChuTriId());
			if (optionalDmDonVi.isPresent()) {
				keHoachThang.setDonViChuTriId(optionalDmDonVi.get().getId());
			}
		}
		keHoachThang.setThang(keHoachThangData.getThang());
		keHoachThang = serviceKeHoachThangService.save(keHoachThang);
		serviceNhiemVuThangService.setFixedDaXoaForKeHoachThangId(true, keHoachThang.getId());
		List<NhiemVuTongHopThangData> nhiemVuTongHopThangDatas = keHoachThangData.getNhiemVuTongHopThangDatas();
		if (Objects.nonNull(nhiemVuTongHopThangDatas) && !nhiemVuTongHopThangDatas.isEmpty()) {
			for (NhiemVuTongHopThangData nhiemVuTongHopThangData : nhiemVuTongHopThangDatas) {
				List<NhiemVuThangData> nhiemVuThangDatas = nhiemVuTongHopThangData.getNhiemVuThangDatas();
				if (Objects.nonNull(nhiemVuThangDatas) && !nhiemVuThangDatas.isEmpty()) {
					for (NhiemVuThangData nhiemVuThangData : nhiemVuThangDatas) {
						NhiemVuThang nhiemVuThang = new NhiemVuThang();
						if (Objects.nonNull(nhiemVuThangData.getId())) {
							Optional<NhiemVuThang> optionalNhiemVuThang = serviceNhiemVuThangService.findById(nhiemVuThangData.getId());
							if (optionalNhiemVuThang.isPresent()) {
								nhiemVuThang = optionalNhiemVuThang.get();
							}
						}
						nhiemVuThang.setDaXoa(false);
						nhiemVuThang.setTenNhiemVu(nhiemVuThangData.getTenNhiemVu());
						nhiemVuThang.setDonViPhoiHop(nhiemVuThangData.getDonViPhoiHop());
						nhiemVuThang.setIsNhiemVuThangTruoc(nhiemVuThangData.getIsNhiemVuThangTruoc());
						nhiemVuThang.setKeHoachThangId(nhiemVuThangData.getId());
						nhiemVuThang.setNhiemVuThangTruocId(nhiemVuThangData.getNhiemVuThangTruocId());
						nhiemVuThang.setThoiGian(nhiemVuThangData.getThoiGian());
						nhiemVuThang.setGhiChu(nhiemVuThangData.getGhiChu());
						nhiemVuThang.setTinhTrang(nhiemVuThangData.getTinhTrang());
						if (Objects.isNull(nhiemVuThang.getTinhTrang())) {
							nhiemVuThang.setTinhTrang(Constants.QLKH_TINHTRANG_CHUATHUCHIEN);
						}
						nhiemVuThang.setCanBoThucHienId(null);
						if (Objects.nonNull(nhiemVuThangData.getCanBoThucHienId())) {
							Optional<DmCanBo> optionalDmCanBo = serviceDmCanBoService.findById(nhiemVuThangData.getCanBoThucHienId());
							if (optionalDmCanBo.isPresent()) {
								nhiemVuThang.setCanBoThucHienId(optionalDmCanBo.get().getId());
							}
						}
						nhiemVuThang = serviceNhiemVuThangService.save(nhiemVuThang);
					}
				}
			}
		}
		return this.convertToKeHoachThangData(keHoachThang);
	}

	public KeHoachThangData delete(Long id) throws EntityNotFoundException {
		Optional<KeHoachThang> optional = serviceKeHoachThangService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(KeHoachThang.class, "id", String.valueOf(id));
		}
		KeHoachThang keHoachThang = optional.get();
		keHoachThang.setDaXoa(true);
		keHoachThang = serviceKeHoachThangService.save(keHoachThang);
		return this.convertToKeHoachThangData(keHoachThang);
	}
}
