package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo_import.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

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

	public Page<ThongTinChoMeoImportData> findAll(Long thongTinChoMeoId, String trangThai, String chuHo, String dienThoai, String loaiDongVat, String giong, int page, int size,
			String sortBy, String sortDir) {

		Direction direction;

		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<ThongTinChoMeoImport> result = serviceThongTinChoMeoImportService.findAll(thongTinChoMeoId, trangThai, chuHo, dienThoai, loaiDongVat, giong,
				PageRequest.of(page, size, direction, sortBy));
		Page<ThongTinChoMeoImportData> data = result.map(this::convertObject);
		return data;

	}

	public ThongTinChoMeoImportData convertObject(ThongTinChoMeoImport object) {
		ThongTinChoMeoImportData result = new ThongTinChoMeoImportData();
		result.setId(object.getId());
		result.setChuHo(object.getChuHo());
		result.setDiaChi(object.getDiaChi());
		result.setDienThoai(object.getDienThoai());
		result.setQuanHuyen(object.getQuanHuyen());
		if(!isValidQuanHuyen(object.getQuanHuyen())) {
			result.setTrangThaiImport(false);;
		}
		result.setPhuongXa(object.getPhuongXa());
		result.setLoaiDongVat(object.getLoaiDongVat());
		result.setGiong(object.getGiong());
		if(!isValidGiong(object.getGiong())) {
			result.setTrangThaiImport(false);;
		}
			
		result.setMauLong(object.getMauLong());
		result.setTenConVat(object.getTenConVat());
		result.setNamSinh(object.getNamSinh());
		result.setTinhBiet(object.getTinhBiet());
		result.setTrangThai(object.getTrangThai());
		System.out.println(result.getTrangThaiImport());
		return result;
		
		
	}

	public ThongTinChoMeoImportData findById(Long id) {
		ThongTinChoMeoImportData result = new ThongTinChoMeoImportData();
		Optional<ThongTinChoMeoImport> opt = serviceThongTinChoMeoImportService.findById(id);
		if (opt.isPresent()) {
			result = this.convertObject(opt.get());
		}
		return result;
	}

	public Integer delete(List<Long> ids) throws EntityNotFoundException {
		List<ThongTinChoMeoImport> result = new ArrayList<ThongTinChoMeoImport>();
		if(ids.size() > 0) {
			ids.stream().forEach(item -> {
				Optional<ThongTinChoMeoImport> optional = serviceThongTinChoMeoImportService.findById(item);
				if (optional.isPresent()) {
					ThongTinChoMeoImport object = optional.get();
					object.setDaXoa(true);
					serviceThongTinChoMeoImportService.save(object);
					result.add(object);
				}
			});
		}
		return result.size();
	}

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
						object.setTrangThaiImport(false);
					} else {
						object.setTrangThaiImport(true);
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
		if (ids.size() > 0) {
			ids.stream().forEach(item -> {
				Optional<ThongTinChoMeoImport> optional = serviceThongTinChoMeoImportService.findById(item);
				if (optional.isPresent()) {
					
					
					ThongTinChoMeoImport object = optional.get();
					if (object.isTrangThaiImport() == true) {
						System.out.println(object);
			
					
					ChuQuanLy chuQuanLy = new ChuQuanLy();
				
					
					
				
//					if (!optChuQuanLy.isPresent()) {
						chuQuanLy.setChuHo(object.getChuHo());
						chuQuanLy.setDiaChi(object.getDiaChi());
						chuQuanLy.setDienThoai(object.getDienThoai());
						List<DmQuanHuyen> quanHuyenList = serviceDmQuanHuyenService
								.findByTenIgnoreCaseEndingWithAndDaXoa(object.getQuanHuyen(), false);
						if (quanHuyenList.size() > 0) {
							chuQuanLy.setQuanHuyen_Id(quanHuyenList.get(0).getId());
							System.out.println(chuQuanLy.getQuanHuyen_Id()+"+-+-+-+-+-+");
					
						}
						List<DmPhuongXa> phuongXaList = serviceDmPhuongXaService
								.findByTenIgnoreCaseEndingWithAndDaXoa(object.getPhuongXa(), false);
						if (phuongXaList.size() > 0) {
							chuQuanLy.setPhuongXa_Id(phuongXaList.get(0).getId());
						}
						chuQuanLy.setDaXoa(false);
						System.out.println("-----------"+chuQuanLy+"----------");
					//	}
						
					
			
					serviceChuQuanLyService.save(chuQuanLy);
						 
					
						ThongTinChoMeo thongTinChoMeo = new ThongTinChoMeo();
						thongTinChoMeo.setMauLong(object.getMauLong());
						thongTinChoMeo.setNamSinh(object.getNamSinh());
					

						if (object.getTinhBiet() != null && object.getTinhBiet().toLowerCase().equals("đực")) {
							thongTinChoMeo.setTinhBiet(0);
						} else if (object.getTinhBiet() != null && object.getTinhBiet().toLowerCase().equals("cái")) {
							thongTinChoMeo.setTinhBiet(1);
						}
						
						thongTinChoMeo.setTenConVat(object.getTenConVat());
						
						if (object.getTrangThai() != null && object.getTrangThai().toLowerCase().equals("bình thường")) {
							thongTinChoMeo.setTrangThai(0);
						} else if (object.getTrangThai() != null && object.getTrangThai().toLowerCase().equals("đã mất")) {
							thongTinChoMeo.setTrangThai(1);
						}else if (object.getTrangThai() != null && object.getTrangThai().toLowerCase().equals("đi lạc")) {
							thongTinChoMeo.setTrangThai(2);
						}
						System.out.println(thongTinChoMeo.getTrangThai());					
						List<DmGiong> giongList = serviceDmGiongService.findByTen(object.getGiong());
						if (giongList.size() > 0) {
							
							thongTinChoMeo.setGiongId(giongList.get(0).getId());
							System.out.println("ten giong"+ object.getGiong()+"---------"+thongTinChoMeo.getGiongId());
						}

					
						if (object.getLoaiDongVat() != null && object.getLoaiDongVat().toLowerCase().equals("mèo")) {
							thongTinChoMeo.setLoaiDongVatId(Long.valueOf(19));
						} else if (object.getTinhBiet() != null && object.getTinhBiet().toLowerCase().equals("chó")) {
							thongTinChoMeo.setLoaiDongVatId(Long.valueOf(25));
						}
						thongTinChoMeo.setDaXoa(false);
						thongTinChoMeo.setChuQuanLyId(chuQuanLy.getId());
						thongTinChoMeo= serviceThongTinChoMeoService.save(thongTinChoMeo);
						object.setThongTinChoMeoId(thongTinChoMeo.getId());
						
						object.setDaXoa(true);
						serviceThongTinChoMeoImportService.save(object);
								
						
						
					
						result.add(object);
					}
				}
			});
		}
		return result.size();
	}

	
	public ThongTinChoMeoImportData update(Long id, ThongTinChoMeoImportData thongTinChoMeoImportData,
			BindingResult result) throws EntityNotFoundException, MethodArgumentNotValidException {
		Optional<ThongTinChoMeoImport> optional = serviceThongTinChoMeoImportService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinChoMeoImportData.class, "id", String.valueOf(id));
		}
		ThongTinChoMeoImport thongTinChoMeoImport = optional.get();
		thongTinChoMeoImport.setId(thongTinChoMeoImportData.getId());
		thongTinChoMeoImport.setChuHo(thongTinChoMeoImportData.getChuHo());
		thongTinChoMeoImport.setDiaChi(thongTinChoMeoImportData.getDiaChi());
		thongTinChoMeoImport.setDienThoai(thongTinChoMeoImportData.getDienThoai());
		thongTinChoMeoImport.setQuanHuyen(thongTinChoMeoImportData.getQuanHuyen());
		thongTinChoMeoImport.setPhuongXa(thongTinChoMeoImportData.getPhuongXa());
		thongTinChoMeoImport.setLoaiDongVat(thongTinChoMeoImportData.getLoaiDongVat());
		thongTinChoMeoImport.setGiong(thongTinChoMeoImportData.getGiong());
		thongTinChoMeoImport.setMauLong(thongTinChoMeoImportData.getMauLong());
		thongTinChoMeoImport.setTenConVat(thongTinChoMeoImportData.getTenConVat());
		thongTinChoMeoImport.setNamSinh(thongTinChoMeoImportData.getNamSinh());
		thongTinChoMeoImport.setTinhBiet(thongTinChoMeoImportData.getTinhBiet());
		thongTinChoMeoImport.setTrangThai(thongTinChoMeoImportData.getTrangThai());
		thongTinChoMeoImport = serviceThongTinChoMeoImportService.save(thongTinChoMeoImport);
		return this.convertObject(thongTinChoMeoImport);
	}
	
	
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