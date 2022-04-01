package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import jdk.internal.org.jline.utils.Log;
import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.core.dao.model.CoreAttachment;
import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.danhmuc.dao.service.DmPhuongXaService;
import vn.dnict.microservice.danhmuc.dao.service.DmQuanHuyenService;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.service.ChuQuanLyService;
import vn.dnict.microservice.nnptnt.chomeo.data.ImportChoMeo;
import vn.dnict.microservice.nnptnt.chomeo.data.ThongTinChoMeoImportData;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.business.ThongTinChoMeoBusiness;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model.ThongTinChoMeo;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.service.ThongTinChoMeoService;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.dao.model.ThongTinChoMeoImport;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.dao.service.ThongTinChoMeoImportService;
import vn.dnict.microservice.nnptnt.dm.giong.dao.model.DmGiong;
import vn.dnict.microservice.nnptnt.dm.giong.dao.service.DmGiongService;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.model.DmLoaiDongVat;
import vn.dnict.microservice.nnptnt.dm.loaidongvat.dao.service.DmLoaiDongVatService;
import vn.dnict.microservice.uaa.dao.service.auth.UaaUserService;
import vn.dnict.microservice.utils.Constants;

@Service
public class ThongTinChoMeoImportBusiness {
	@Autowired
	private CoreAttachmentBusiness coreAttachmentBusiness;
	@Autowired
	private ExcelReturnObject excelReturnObject;
	@Autowired
	private DmPhuongXaService serviceDmPhuongXaService;
	@Autowired
	private DmQuanHuyenService serviceDmQuanHuyenService;
	@Autowired
	protected UaaUserService uaaUserService;
	@Autowired
	private ThongTinChoMeoImportService serviceThongTinChoMeoImportService;
	@Autowired
	private ThongTinChoMeoService serviceThongTinChoMeoService;
	@Autowired
	private ChuQuanLyService serviceChuQuanLyService;

	@Autowired
	private DmGiongService serviceDmGiongService;
	@Autowired
	private DmLoaiDongVatService serviceDmLoaiDongVatService;



	public Boolean getDataImport(Long fileImportId) throws IOException {
		if (fileImportId != null && fileImportId > 0) {
			int type = Constants.DINH_KEM_1_FILE;
			long objectId = 0;
			String appCode = ThongTinChoMeoImport.class.getSimpleName();
			CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileImportId, objectId, type, appCode);
			String[] keyArray = { "stt", "tenchuho", "diachi", "quanhuyen", "phuongxa", "dienthoai", "loaidongvat",
					"giong", "tenconvat", "namsinh", "maulong", "tinhbiet", "trangthai" };
			String[] checkTinhBiet = { "duc", "cai" };
			List<String> checkTB = Arrays.asList(checkTinhBiet);
			String[] checkTrangThai = { "binhthuong", "damat", "dilac" };
			List<String> checkTT = Arrays.asList(checkTrangThai);

			List<HashMap<String, String>> listConvert = excelReturnObject.excel2ObjectIndex(coreAttachment, keyArray,
					0);
			List<ThongTinChoMeoImport> listObjectTemp = new ArrayList<ThongTinChoMeoImport>();
			// Log.info(listObjectTemp.size());

			if (listConvert.size() > 0) {
				listConvert.parallelStream().forEach(item -> {

					ThongTinChoMeoImport object = new ThongTinChoMeoImport();
//					object.setChuHo(item.get("tenchuho"));
//					object.setDiaChi(item.get("diachi"));
//					object.setDienThoai(item.get("dienthoai"));
//					object.setPhuongXa(item.get("phuongxa"));
//					object.setQuanHuyen(item.get("quanhuyen"));
//					object.setLoaiDongVat(item.get("loaidongvat"));
//					object.setTenConVat(item.get("tenconvat"));
//					object.setNamSinh(item.get("namsinh"));
//					object.setTinhBiet(item.get("tinhbiet"));
//					object.setTrangThai(item.get("trangthai"));
//					object.setGiong(item.get("giong"));

					boolean checkFlat = false;
					if (item.get("tenchuho") != null) {
						object.setChuHo(item.get("tenchuho"));
					} else {
						checkFlat = true;
					}
					if (item.get("dienthoai") != null) {
						object.setDienThoai(item.get("dienthoai"));
					} else {
						checkFlat = true;
					}

					if (item.get("diachi") != null) {
						object.setDiaChi(item.get("diachi"));
					} else {
						checkFlat = true;
					}

					if (item.get("phuongxa") != null) {
						object.setPhuongXa(item.get("phuongxa"));
						if (!isValidPhuongXa(item.get("phuongxa"))) {
							checkFlat = true;
						}
					} else {
						checkFlat = true;
					}

					if (item.get("quanhuyen") != null) {
						object.setQuanHuyen(item.get("quanhuyen"));
						if (!isValidQuanHuyen(item.get("quanhuyen"))) {
							checkFlat = true;
						}
					}

					object.setTenConVat(item.get("tenconvat"));
					object.setMauLong(item.get("maulong"));
					object.setNamSinh(item.get("namsinh"));

					if (item.get("tinhbiet") != null) {
						object.setTinhBiet(item.get("tinhbiet"));
						if (!checkTB.contains(item.get("tinhbiet").toLowerCase())) {
							checkFlat = true;
						}
					} else {
						checkFlat = true;
					}
					if (item.get("trangthai") != null) {
						object.setTrangThai(item.get("trangthai"));
						if (!checkTT.contains(item.get("trangthai").toLowerCase())) {
							checkFlat = true;
						}
					} else {
						checkFlat = true;
					}
					if (item.get("loaidongvat") != null) {
						object.setLoaiDongVat(item.get("loaidongvat"));
						if (!isValidLoaiDongVat(item.get("loaidongvat")))
							;
						{
							checkFlat = true;
						}
					} else {
						checkFlat = true;
					}
					if (item.get("giong") != null) {
						object.setGiong(item.get("giong"));
						if (!isValidGiong(item.get("giong"))) {
							checkFlat = true;
						}
					}

					if (checkFlat == true) {
						object.setTrangThaiImport(true);
					} else {
						object.setTrangThaiImport(false);
					}
					object = serviceThongTinChoMeoImportService.save(object);
					listObjectTemp.add(object);
				});
			}
			if (listObjectTemp.size() > 0) {
				return true;
			}
		}
		return false;
	}
	
public Integer save(List<Long> ids) throws EntityNotFoundException {
	List<ThongTinChoMeoImport> result = new ArrayList<ThongTinChoMeoImport>();
	if(ids.size() > 0) {
		ids.stream().forEach(item -> {
			Optional<ThongTinChoMeoImport> optional =serviceThongTinChoMeoImportService.findById(item);
			if(optional.isPresent()) {
				ThongTinChoMeoImport object = optional.get();
				if(object.isTrangThaiImport() ==true) {
					Optional<ChuQuanLy> optChuQuanLy = serviceChuQuanLyService.findByChuHoAndDiaChi(object.getChuHo(),
							object.getDiaChi());
					ChuQuanLy chuQuanLy = new ChuQuanLy();
					chuQuanLy = optChuQuanLy.get();
					if (optChuQuanLy.isPresent()) {
						chuQuanLy.setChuHo(object.getChuHo());
						chuQuanLy.setDiaChi(object.getDiaChi());
						chuQuanLy.setDienThoai(object.getDienThoai());
						List<DmQuanHuyen> quanHuyenList = serviceDmQuanHuyenService
								.findByTenIgnoreCaseEndingWithAndDaXoa(object.getQuanHuyen(), false);
						if (quanHuyenList.size() > 0) {
							chuQuanLy.setQuanHuyen_Id(quanHuyenList.get(0).getId());
							List<DmPhuongXa> phuongXaList = serviceDmPhuongXaService
									.findByTenIgnoreCaseEndingWithAndDaXoa(object.getPhuongXa(), false);
							if (phuongXaList.size() > 0) {
								chuQuanLy.setPhuongXa_Id(phuongXaList.get(0).getId());
							}
						}
						
						serviceChuQuanLyService.save(chuQuanLy);
					}
						ThongTinChoMeo objThongTinChoMeo = new ThongTinChoMeo();
						List<DmGiong> giongList = serviceDmGiongService.findByTen(object.getGiong());
						if (giongList.size() > 0) {
							objThongTinChoMeo.setGiongId(giongList.get(0).getId());
						}
						List<DmLoaiDongVat> loaiDongVatList = serviceDmLoaiDongVatService.findByTen(object.getLoaiDongVat());
						if (loaiDongVatList.size() > 0) {
							objThongTinChoMeo.setLoaiDongVatId(loaiDongVatList.get(0).getId());
						}
						switch (object.getTinhBiet().toLowerCase().trim()) {
						case "duc":
							objThongTinChoMeo.setTinhBiet(Integer.valueOf(1));
						case "cai":
							objThongTinChoMeo.setTinhBiet(Integer.valueOf(2));
						}

						objThongTinChoMeo.setMauLong(object.getMauLong());

						switch (object.getTrangThai().toLowerCase().trim()) {
						case "binhthuong":
							objThongTinChoMeo.setTrangThai(Integer.valueOf(1));
						case "damat":
							objThongTinChoMeo.setTrangThai(Integer.valueOf(2));
						case "dilac":
							objThongTinChoMeo.setTrangThai(Integer.valueOf(3));
						}

						objThongTinChoMeo.setTenConVat(object.getTenConVat());
						objThongTinChoMeo.setNamSinh(object.getNamSinh());

						serviceThongTinChoMeoService.save(objThongTinChoMeo);
						
						result.add(object);
						}
			
					
				
			}
				
		});
	}
	return result.size();
}
//	public HashMap<String, String> saveImport(ImportChoMeo input) throws IOException {
//
//		List<ThongTinChoMeoImport> list = input.getListImport();
//		List<ThongTinChoMeo> listRes = new ArrayList<ThongTinChoMeo>();
//		List<String> countRowError = new ArrayList<String>();
//		List<Long> checkRows = new ArrayList<Long>();
//		if (list != null && list.size() > 0) {
//			list.parallelStream().forEach(item -> {
//				Optional<ChuQuanLy> optChuQuanLy = serviceChuQuanLyService.findByChuHoAndDiaChi(item.getChuHo(),
//						item.getDiaChi());
//				checkRows.add(item.getId());
//				ChuQuanLy chuQuanLy = new ChuQuanLy();
//				chuQuanLy = optChuQuanLy.get();
//				if (!optChuQuanLy.isPresent()) {
//					chuQuanLy.setChuHo(item.getChuHo());
//					chuQuanLy.setDiaChi(item.getDiaChi());
//					chuQuanLy.setDienThoai(item.getDienThoai());
//					List<DmQuanHuyen> quanHuyenList = serviceDmQuanHuyenService
//							.findByTenIgnoreCaseEndingWithAndDaXoa(item.getQuanHuyen(), false);
//					if (quanHuyenList.size() > 0) {
//						chuQuanLy.setQuanHuyen_Id(quanHuyenList.get(0).getId());
//						List<DmPhuongXa> phuongXaList = serviceDmPhuongXaService
//								.findByTenIgnoreCaseEndingWithAndDaXoa(item.getPhuongXa(), false);
//						if (phuongXaList.size() > 0) {
//							chuQuanLy.setPhuongXa_Id(phuongXaList.get(0).getId());
//						}
//					}
//
//					chuQuanLy = serviceChuQuanLyService.save(chuQuanLy);
//
//					ThongTinChoMeo objThongTinChoMeo = new ThongTinChoMeo();
//					List<DmGiong> giongList = serviceDmGiongService.findByTen(item.getGiong());
//					if (giongList.size() > 0) {
//						objThongTinChoMeo.setGiongId(giongList.get(0).getId());
//					}
//					List<DmLoaiDongVat> loaiDongVatList = serviceDmLoaiDongVatService.findByTen(item.getLoaiDongVat());
//					if (loaiDongVatList.size() > 0) {
//						objThongTinChoMeo.setLoaiDongVatId(loaiDongVatList.get(0).getId());
//					}
//					switch (item.getTinhBiet().toLowerCase().trim()) {
//					case "duc":
//						objThongTinChoMeo.setTinhBiet(Integer.valueOf(1));
//					case "cai":
//						objThongTinChoMeo.setTinhBiet(Integer.valueOf(2));
//					}
//
//					objThongTinChoMeo.setMauLong(item.getMauLong());
//
//					switch (item.getTrangThai().toLowerCase().trim()) {
//					case "binhthuong":
//						objThongTinChoMeo.setTrangThai(Integer.valueOf(1));
//					case "damat":
//						objThongTinChoMeo.setTrangThai(Integer.valueOf(2));
//					case "dilac":
//						objThongTinChoMeo.setTrangThai(Integer.valueOf(3));
//					}
//
//					objThongTinChoMeo.setTenConVat(item.getTenConVat());
//					objThongTinChoMeo.setNamSinh(item.getNamSinh());
//
//					ThongTinChoMeo result = serviceThongTinChoMeoService.save(objThongTinChoMeo);
//					if (result.getId() != null) {
//						listRes.add(result);
//					}
//				} else {
//					countRowError.add("error");
//				}
//
//			});
//		}
//
//		return  countRowError ;
//
//	}

	public boolean isValidPhuongXa(String phuongXaString) {
		try {
			List<DmPhuongXa> obj = serviceDmPhuongXaService.findByTenIgnoreCaseEndingWithAndDaXoa(phuongXaString,
					false);

			if (obj.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public boolean isValidQuanHuyen(String quanHuyenString) {
		try {
			List<DmQuanHuyen> obj = serviceDmQuanHuyenService.findByTenIgnoreCaseEndingWithAndDaXoa(quanHuyenString,
					false);

			if (obj.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public boolean isValidLoaiDongVat(String tenString) {
		try {
			List<DmLoaiDongVat> obj = serviceDmLoaiDongVatService.findByTen(tenString);
			if (obj.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}

	public boolean isValidGiong(String tenString) {
		try {
			List<DmGiong> obj = serviceDmGiongService.findByTen(tenString);
			if (obj.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}

	public boolean isValidDienThoai(String dienThoai) {
		try {
			List<ChuQuanLy> obj = serviceChuQuanLyService.findByDienThoai(dienThoai);
			if (obj.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}
}