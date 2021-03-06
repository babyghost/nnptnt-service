package vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.core.dao.model.CoreAttachment;
import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.hopdong.data.TinhHinhThucHienHopDongInput;
import vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.model.ThongTinHopDong;
import vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.service.ThongTinHopDongService;
import vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.model.TinhHinhThucHienHopDong;
import vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.service.TinhHinhThucHienHopDongService;
import vn.dnict.microservice.utils.Constants;

@Service
@Slf4j
public class TinhHinhThucHienHopDongBusiness {
	@Autowired
	private TinhHinhThucHienHopDongService serviceTinhHinhThucHienHopDongService;
	@Autowired
	private ThongTinHopDongService serviceThongTinHopDongService;
	@Autowired
	private CoreAttachmentBusiness coreAttachmentBusiness;

	public List<TinhHinhThucHienHopDongInput> getListByHopDongId(Long hopDongId) {
		List<TinhHinhThucHienHopDong> listTinhHinhThucHienHopDong = serviceTinhHinhThucHienHopDongService
				.findByHopDongIdAndDaXoaOrderByThanhToanDotDesc(hopDongId, false);
		List<TinhHinhThucHienHopDongInput> listTinhHinhThucHienHopDongInput = new ArrayList<>();
		if (listTinhHinhThucHienHopDong != null && !listTinhHinhThucHienHopDong.isEmpty()) {
			for (TinhHinhThucHienHopDong tinhHinhThucHienHopDong : listTinhHinhThucHienHopDong) {
				TinhHinhThucHienHopDongInput tinhHinhThucHienHopDongInput = new TinhHinhThucHienHopDongInput();
				tinhHinhThucHienHopDongInput.setId(tinhHinhThucHienHopDong.getId());
				tinhHinhThucHienHopDongInput.setHopDongId(tinhHinhThucHienHopDong.getHopDongId());
				tinhHinhThucHienHopDongInput.setThanhToanDot(tinhHinhThucHienHopDong.getThanhToanDot());
				tinhHinhThucHienHopDongInput.setThanhToanNoiDung(tinhHinhThucHienHopDong.getThanhToanNoiDung());
				tinhHinhThucHienHopDongInput.setThanhToanGiaTri(tinhHinhThucHienHopDong.getThanhToanGiaTri());
				tinhHinhThucHienHopDongInput.setThanhToanSoChungTu(tinhHinhThucHienHopDong.getThanhToanSoChungTu());
				tinhHinhThucHienHopDongInput.setThanhToanNgay(tinhHinhThucHienHopDong.getThanhToanNgay());
				tinhHinhThucHienHopDongInput.setHoaDonSo(tinhHinhThucHienHopDong.getHoaDonSo());
				tinhHinhThucHienHopDongInput.setHoaDonNgay(tinhHinhThucHienHopDong.getHoaDonNgay());
				tinhHinhThucHienHopDongInput.setGiaTriConLai(tinhHinhThucHienHopDong.getGiaTriConLai());
				tinhHinhThucHienHopDongInput.setLyDo(tinhHinhThucHienHopDong.getLyDo());
				
				if(Objects.nonNull(tinhHinhThucHienHopDong)) {
					int type = Constants.DINH_KEM_1_FILE;
					Long fileDinhKemId = null;
					Long objecId = tinhHinhThucHienHopDong.getHopDongId();
					String appCode = TinhHinhThucHienHopDong.class.getSimpleName();
					FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments(tinhHinhThucHienHopDong.getThanhToanFileDinhKemId(), appCode, objecId, type);
					tinhHinhThucHienHopDongInput.setListThanhToanFileDinhKemId(fileDinhKem.getIds());
					tinhHinhThucHienHopDongInput.setListThanhToanFileDinhKem(fileDinhKem.getFileLists());
				}
				if(Objects.nonNull(tinhHinhThucHienHopDong)) {
					int type = Constants.DINH_KEM_1_FILE;
					Long fileDinhKemId = null;
					Long objecId = tinhHinhThucHienHopDong.getHopDongId();
					String appCode = TinhHinhThucHienHopDong.class.getSimpleName();
					FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments(tinhHinhThucHienHopDong.getHoaDonFileDinhKemId(), appCode, objecId, type);
					tinhHinhThucHienHopDongInput.setListHoaDonFileDinhKemId(fileDinhKem.getIds());
					tinhHinhThucHienHopDongInput.setListHoaDonFileDinhKem(fileDinhKem.getFileLists());
				}
				listTinhHinhThucHienHopDongInput.add(tinhHinhThucHienHopDongInput);
			}
		}
		return listTinhHinhThucHienHopDongInput;
	}

	public TinhHinhThucHienHopDongInput findById(Long hopDongId, Long tinhHinhThucHienId)
			throws EntityNotFoundException {
		Optional<ThongTinHopDong> optionalThongTinHopDong = serviceThongTinHopDongService
				.findById(hopDongId);
		if (!optionalThongTinHopDong.isPresent()) {
			throw new EntityNotFoundException(ThongTinHopDong.class, "id", hopDongId.toString());
		}
		Optional<TinhHinhThucHienHopDong> optionalTinhHinhThucHienHopDong = serviceTinhHinhThucHienHopDongService
				.findById(tinhHinhThucHienId);
		if (!optionalTinhHinhThucHienHopDong.isPresent()) {
			throw new EntityNotFoundException(TinhHinhThucHienHopDong.class, "id",
					tinhHinhThucHienId.toString());
		}

		TinhHinhThucHienHopDongInput tinhHinhThucHienHopDongInput = new TinhHinhThucHienHopDongInput();
		TinhHinhThucHienHopDong tinhHinhThucHienHopDong = optionalTinhHinhThucHienHopDong.get();
		tinhHinhThucHienHopDongInput.setId(tinhHinhThucHienHopDong.getId());
		tinhHinhThucHienHopDongInput.setHopDongId(tinhHinhThucHienHopDong.getHopDongId());
		tinhHinhThucHienHopDongInput.setThanhToanDot(tinhHinhThucHienHopDong.getThanhToanDot());
		tinhHinhThucHienHopDongInput.setThanhToanNoiDung(tinhHinhThucHienHopDong.getThanhToanNoiDung());
		tinhHinhThucHienHopDongInput.setThanhToanGiaTri(tinhHinhThucHienHopDong.getThanhToanGiaTri());
		tinhHinhThucHienHopDongInput.setThanhToanSoChungTu(tinhHinhThucHienHopDong.getThanhToanSoChungTu());
		tinhHinhThucHienHopDongInput.setThanhToanNgay(tinhHinhThucHienHopDong.getThanhToanNgay());
		tinhHinhThucHienHopDongInput.setHoaDonSo(tinhHinhThucHienHopDong.getHoaDonSo());
		tinhHinhThucHienHopDongInput.setHoaDonNgay(tinhHinhThucHienHopDong.getHoaDonNgay());
		Double giaTriConlai = 0D;
		if (tinhHinhThucHienHopDong.getGiaTriConLai() != null) {
			giaTriConlai = tinhHinhThucHienHopDong.getGiaTriConLai();
		} else {
			giaTriConlai = optionalThongTinHopDong.get().getGiaTriConLai();
		}
		tinhHinhThucHienHopDongInput.setGiaTriConLai(giaTriConlai);
		tinhHinhThucHienHopDongInput.setLyDo(tinhHinhThucHienHopDong.getLyDo());
		
		if(Objects.nonNull(tinhHinhThucHienHopDong)) {
			int type = Constants.DINH_KEM_1_FILE;
			Long fileDinhKemId = null;
			Long objecId = tinhHinhThucHienHopDong.getId();
			String appCode = TinhHinhThucHienHopDong.class.getSimpleName();
			FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments(tinhHinhThucHienHopDong.getThanhToanFileDinhKemId(), appCode, objecId, type);
			tinhHinhThucHienHopDongInput.setListThanhToanFileDinhKemId(fileDinhKem.getIds());
			tinhHinhThucHienHopDongInput.setListThanhToanFileDinhKem(fileDinhKem.getFileLists());
		}
		if(Objects.nonNull(tinhHinhThucHienHopDong)) {
			int type = Constants.DINH_KEM_1_FILE;
			Long fileDinhKemId = null;
			Long objecId = tinhHinhThucHienHopDong.getId();
			String appCode = TinhHinhThucHienHopDong.class.getSimpleName();
			FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments(tinhHinhThucHienHopDong.getHoaDonFileDinhKemId(), appCode, objecId, type);
			tinhHinhThucHienHopDongInput.setListHoaDonFileDinhKemId(fileDinhKem.getIds());
			tinhHinhThucHienHopDongInput.setListHoaDonFileDinhKem(fileDinhKem.getFileLists());
		}
		return tinhHinhThucHienHopDongInput;
	}
	
	

	public FileDinhKem getFileDinhKemHoaDon(Long hopDongId, Long tinhHinhThucHienId) throws EntityNotFoundException {
		Optional<ThongTinHopDong> optionalThongTinHopDong = serviceThongTinHopDongService
				.findById(hopDongId);
		if (!optionalThongTinHopDong.isPresent()) {
			throw new EntityNotFoundException(ThongTinHopDong.class, "id", hopDongId.toString());
		}
		Optional<TinhHinhThucHienHopDong> optional = serviceTinhHinhThucHienHopDongService
				.findById(tinhHinhThucHienId);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(TinhHinhThucHienHopDong.class, "id",
					tinhHinhThucHienId.toString());
		}
		int type = Constants.DINH_KEM_1_FILE;
		Long fileDinhKemId = optional.get().getHoaDonFileDinhKemId();
		Long objectId = optional.get().getId();
		String appCode = TinhHinhThucHienHopDong.class.getSimpleName();
		return coreAttachmentBusiness.getAttachments(fileDinhKemId, appCode, objectId, type);
	}

	public FileDinhKem getFileDinhKemThanhToan(Long hopDongId, Long tinhHinhThucHienId) throws EntityNotFoundException {
		Optional<ThongTinHopDong> optionalThongTinHopDong = serviceThongTinHopDongService
				.findById(hopDongId);
		if (!optionalThongTinHopDong.isPresent()) {
			throw new EntityNotFoundException(ThongTinHopDong.class, "id", hopDongId.toString());
		}
		Optional<TinhHinhThucHienHopDong> optional = serviceTinhHinhThucHienHopDongService
				.findById(tinhHinhThucHienId);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(TinhHinhThucHienHopDong.class, "id",
					tinhHinhThucHienId.toString());
		}
		int type = Constants.DINH_KEM_1_FILE;
		Long fileDinhKemId = optional.get().getThanhToanFileDinhKemId();
		Long objectId = optional.get().getId();
		String appCode = TinhHinhThucHienHopDong.class.getSimpleName();
		return coreAttachmentBusiness.getAttachments(fileDinhKemId, appCode, objectId, type);
	}

	public TinhHinhThucHienHopDong create(Long hopDongId, TinhHinhThucHienHopDongInput tinhHinhThucHienHopDongInput) 
			throws EntityNotFoundException {
		Optional<ThongTinHopDong> optionalThongTinHopDong = serviceThongTinHopDongService
				.findById(hopDongId);
		if (!optionalThongTinHopDong.isPresent()) {
			throw new EntityNotFoundException(ThongTinHopDong.class, "id", hopDongId.toString());
		}
		// log.debug("Tinh hinh input : " + TinhHinhThucHienHopDongInput);
		// c???p nh???t tr???ng th??i ???? thanh to??n
		ThongTinHopDong thongTinHopDong = optionalThongTinHopDong.get();

		TinhHinhThucHienHopDong tinhHinhThucHienHopDong = new TinhHinhThucHienHopDong();
		tinhHinhThucHienHopDong.setDaXoa(false);
		tinhHinhThucHienHopDong.setHopDongId(thongTinHopDong.getId());
		tinhHinhThucHienHopDong.setThanhToanDot(tinhHinhThucHienHopDongInput.getThanhToanDot());
		tinhHinhThucHienHopDong.setThanhToanNoiDung(tinhHinhThucHienHopDongInput.getThanhToanNoiDung());
		tinhHinhThucHienHopDong.setThanhToanGiaTri(tinhHinhThucHienHopDongInput.getThanhToanGiaTri());
		tinhHinhThucHienHopDong.setThanhToanSoChungTu(tinhHinhThucHienHopDongInput.getThanhToanSoChungTu());
		tinhHinhThucHienHopDong.setThanhToanNgay(tinhHinhThucHienHopDongInput.getThanhToanNgay());
		tinhHinhThucHienHopDong.setHoaDonSo(tinhHinhThucHienHopDongInput.getHoaDonSo());
		tinhHinhThucHienHopDong.setHoaDonNgay(tinhHinhThucHienHopDongInput.getHoaDonNgay());
		tinhHinhThucHienHopDong.setGiaTriConLai(tinhHinhThucHienHopDongInput.getGiaTriConLai());
		tinhHinhThucHienHopDong.setLyDo(tinhHinhThucHienHopDongInput.getLyDo());
		tinhHinhThucHienHopDong = serviceTinhHinhThucHienHopDongService.save(tinhHinhThucHienHopDong);

		// x??? l?? gi?? tr??? thanh to??n
		List<TinhHinhThucHienHopDong> listTinhHinhThucHienHopDong = serviceTinhHinhThucHienHopDongService
				.findByHopDongIdAndDaXoaOrderByThanhToanDotDesc(thongTinHopDong.getId(), false);
		Double giaTriConLai = 0D;
		Double tongGiaTriThanhToan = 0D;
		if (listTinhHinhThucHienHopDong != null && !listTinhHinhThucHienHopDong.isEmpty()) {
			for (TinhHinhThucHienHopDong tinhHinhThucHien : listTinhHinhThucHienHopDong) {
				if (tinhHinhThucHienHopDong.getThanhToanGiaTri() != null) {
					tongGiaTriThanhToan += tinhHinhThucHienHopDong.getThanhToanGiaTri();
				}
			}
		}
		if (thongTinHopDong.getGiaTri() != null) {
			giaTriConLai = thongTinHopDong.getGiaTri() - tongGiaTriThanhToan;
		}
		thongTinHopDong.setGiaTriConLai(giaTriConLai);
		if (giaTriConLai != null && giaTriConLai <= 0) {
			thongTinHopDong.setTrangThai(Constants.KHTC_QLHD_TRANG_THAI_DA_THANH_TOAN);
		} else if (giaTriConLai != null && giaTriConLai < thongTinHopDong.getGiaTri()) {
			thongTinHopDong.setTrangThai(Constants.KHTC_QLHD_TRANG_THAI_DANG_THANH_TOAN);
		} else {
			thongTinHopDong.setTrangThai(Constants.KHTC_QLHD_TRANG_THAI_CHUA_THANH_TOAN);
		}
		thongTinHopDong = serviceThongTinHopDongService.save(thongTinHopDong);
		// x??? l?? ????nh k??m HoaDonFileDinhKem
		/* Begin ????nh k??m file *******************************************************/

		/*
		 * Kh???i t???o bi???n **************************************************************
		 * - fileDinhKemIds: danh s??ch id file ???? ????nh k??m ****************************
		 * - type: lo???i ????nh k??m (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) *************
		 * - objectId: id ?????i t?????ng ????nh k??m ******************************************
		 * - appCode: t??n model c???a ?????i t?????ng ????nh k??m*********************************
		 */
		List<Long> fileDinhKemIds = tinhHinhThucHienHopDongInput.getListHoaDonFileDinhKemId();
		int type = Constants.DINH_KEM_1_FILE;
		long objectId = tinhHinhThucHienHopDong.getId();
		String appCode = TinhHinhThucHienHopDong.class.getSimpleName();
		/* X??a m???m ????nh k??m c?? n???u c?? tr?????c khi set file ????nh k??m n???u ????nh k??m nhi???u */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type,
						appCode);

				/* set db n???u c?? tr?????ng l??u v?? chuy???n file t??? temp sang th?? m???c ch??nh */
				if (coreAttachment.getId() > 0) {
					tinhHinhThucHienHopDong.setHoaDonFileDinhKemId(coreAttachment.getId());
					serviceTinhHinhThucHienHopDongService.save(tinhHinhThucHienHopDong);
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
				}

				/* tho??t n???u ????nh k??m 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}
		/* End ????nh k??m file **********************************************************/

		// x??? l?? ????nh k??m HopDongFileDinhKem
		/* Begin ????nh k??m file *******************************************************/

		/*
		 * Kh???i t???o bi???n **************************************************************
		 * - fileDinhKemIds: danh s??ch id file ???? ????nh k??m ****************************
		 * - type: lo???i ????nh k??m (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) *************
		 * - objectId: id ?????i t?????ng ????nh k??m ******************************************
		 * - appCode: t??n model c???a ?????i t?????ng ????nh k??m*********************************
		 */
		fileDinhKemIds = tinhHinhThucHienHopDongInput.getListThanhToanFileDinhKemId();
		type = Constants.DINH_KEM_1_FILE;
		objectId = tinhHinhThucHienHopDong.getId();
		appCode = TinhHinhThucHienHopDong.class.getSimpleName();
		/* X??a m???m ????nh k??m c?? n???u c?? tr?????c khi set file ????nh k??m n???u ????nh k??m nhi???u */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type,
						appCode);

				/* set db n???u c?? tr?????ng l??u v?? chuy???n file t??? temp sang th?? m???c ch??nh */
				if (coreAttachment.getId() > 0) {
					tinhHinhThucHienHopDong.setThanhToanFileDinhKemId(coreAttachment.getId());
					serviceTinhHinhThucHienHopDongService.save(tinhHinhThucHienHopDong);
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
				}

				/* tho??t n???u ????nh k??m 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}
		/* End ????nh k??m file **********************************************************/
		return tinhHinhThucHienHopDong;
	}

	public TinhHinhThucHienHopDong update(Long hopDongId, Long tinhHinhThucHienId,
			TinhHinhThucHienHopDongInput tinhHinhThucHienHopDongInput) throws EntityNotFoundException {
		Optional<ThongTinHopDong> optionalThongTinHopDong = serviceThongTinHopDongService
				.findById(hopDongId);
		if (!optionalThongTinHopDong.isPresent()) {
			throw new EntityNotFoundException(ThongTinHopDong.class, "id", hopDongId.toString());
		}
		Optional<TinhHinhThucHienHopDong> optionalTinhHinhThucHienHopDong = serviceTinhHinhThucHienHopDongService
				.findById(tinhHinhThucHienId);
		if (!optionalTinhHinhThucHienHopDong.isPresent()) {
			throw new EntityNotFoundException(TinhHinhThucHienHopDong.class, "id",
					tinhHinhThucHienId.toString());
		}

		// c???p nh???t tr???ng th??i ???? thanh to??n
		ThongTinHopDong thongTinHopDong = optionalThongTinHopDong.get();

		TinhHinhThucHienHopDong tinhHinhThucHienHopDong = optionalTinhHinhThucHienHopDong.get();
		tinhHinhThucHienHopDong.setDaXoa(false);
		tinhHinhThucHienHopDong.setHopDongId(thongTinHopDong.getId());
		tinhHinhThucHienHopDong.setThanhToanDot(tinhHinhThucHienHopDongInput.getThanhToanDot());
		tinhHinhThucHienHopDong.setThanhToanNoiDung(tinhHinhThucHienHopDongInput.getThanhToanNoiDung());
		tinhHinhThucHienHopDong.setThanhToanGiaTri(tinhHinhThucHienHopDongInput.getThanhToanGiaTri());
		tinhHinhThucHienHopDong.setThanhToanSoChungTu(tinhHinhThucHienHopDongInput.getThanhToanSoChungTu());
		tinhHinhThucHienHopDong.setThanhToanNgay(tinhHinhThucHienHopDongInput.getThanhToanNgay());
		tinhHinhThucHienHopDong.setHoaDonSo(tinhHinhThucHienHopDongInput.getHoaDonSo());
		tinhHinhThucHienHopDong.setHoaDonNgay(tinhHinhThucHienHopDongInput.getHoaDonNgay());
		tinhHinhThucHienHopDong.setGiaTriConLai(tinhHinhThucHienHopDongInput.getGiaTriConLai());
		tinhHinhThucHienHopDong.setLyDo(tinhHinhThucHienHopDongInput.getLyDo());
		Double giaTriTinhHinhConLai = 0D;
		Double giaTriTinhHinhConLaiInput = 0D;
		if (tinhHinhThucHienHopDong.getGiaTriConLai() != null) {
			giaTriTinhHinhConLai = tinhHinhThucHienHopDong.getGiaTriConLai();
		}
		if (tinhHinhThucHienHopDongInput.getGiaTriConLai() != null) {
			giaTriTinhHinhConLaiInput = tinhHinhThucHienHopDongInput.getGiaTriConLai();
		}
		Double soTienCapNhat = giaTriTinhHinhConLai - giaTriTinhHinhConLaiInput;
		tinhHinhThucHienHopDong = serviceTinhHinhThucHienHopDongService.save(tinhHinhThucHienHopDong);
		// C???p nh???t d??? li???u ?????t thanh to??n sau ?????t hi???n t???i

		List<TinhHinhThucHienHopDong> listTinhHinhThanhToanSauDotHienTai = serviceTinhHinhThucHienHopDongService
				.findByHopDongIdAndThanhToanDotGreaterThanAndDaXoa(thongTinHopDong.getId(),
						tinhHinhThucHienHopDong.getThanhToanDot(), false);
		if (listTinhHinhThanhToanSauDotHienTai != null && !listTinhHinhThanhToanSauDotHienTai.isEmpty()) {
			for (TinhHinhThucHienHopDong tinhHinh : listTinhHinhThanhToanSauDotHienTai) {
				Double giaTriCl = 0D;
				if (tinhHinh.getGiaTriConLai() != null) {
					giaTriCl = tinhHinh.getGiaTriConLai();
				}
				tinhHinh.setGiaTriConLai(giaTriCl - soTienCapNhat);
				serviceTinhHinhThucHienHopDongService.save(tinhHinh);
			}
		}
		// x??? l?? gi?? tr??? thanh to??n
		List<TinhHinhThucHienHopDong> listTinhHinhThucHienHopDong = serviceTinhHinhThucHienHopDongService
				.findByHopDongIdAndDaXoaOrderByThanhToanDotDesc(thongTinHopDong.getId(), false);
		Double giaTriConLai = 0D;
		Double tongGiaTriThanhToan = 0D;
		if (listTinhHinhThucHienHopDong != null && !listTinhHinhThucHienHopDong.isEmpty()) {
			for (TinhHinhThucHienHopDong tinhHinhThucHien : listTinhHinhThucHienHopDong) {
				if (tinhHinhThucHienHopDong.getThanhToanGiaTri() != null) {
					tongGiaTriThanhToan += tinhHinhThucHienHopDong.getThanhToanGiaTri();
				}
			}
			if (thongTinHopDong.getGiaTri() != null) {
				giaTriConLai = thongTinHopDong.getGiaTri() - tongGiaTriThanhToan;
			}
			thongTinHopDong.setGiaTriConLai(giaTriConLai);
			if (giaTriConLai != null && giaTriConLai <= 0) {
				thongTinHopDong.setTrangThai(Constants.KHTC_QLHD_TRANG_THAI_DA_THANH_TOAN);
			} else if (giaTriConLai != null && giaTriConLai < thongTinHopDong.getGiaTri()) {
				thongTinHopDong.setTrangThai(Constants.KHTC_QLHD_TRANG_THAI_DANG_THANH_TOAN);
			} else {
				thongTinHopDong.setTrangThai(Constants.KHTC_QLHD_TRANG_THAI_CHUA_THANH_TOAN);
			}
			thongTinHopDong = serviceThongTinHopDongService.save(thongTinHopDong);
		}

		// x??? l?? ????nh k??m HoaDonFileDinhKem
		/* Begin ????nh k??m file *******************************************************/

		/*
		 * Kh???i t???o bi???n **************************************************************
		 * - fileDinhKemIds: danh s??ch id file ???? ????nh k??m ****************************
		 * - type: lo???i ????nh k??m (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) *************
		 * - objectId: id ?????i t?????ng ????nh k??m ******************************************
		 * - appCode: t??n model c???a ?????i t?????ng ????nh k??m*********************************
		 */
		List<Long> fileDinhKemIds = tinhHinhThucHienHopDongInput.getListHoaDonFileDinhKemId();
		int type = Constants.DINH_KEM_1_FILE;
		long objectId = tinhHinhThucHienHopDong.getId();
		String appCode = TinhHinhThucHienHopDong.class.getSimpleName();
		/* X??a m???m ????nh k??m c?? n???u c?? tr?????c khi set file ????nh k??m n???u ????nh k??m nhi???u */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type,
						appCode);

				/* set db n???u c?? tr?????ng l??u v?? chuy???n file t??? temp sang th?? m???c ch??nh */
				if (coreAttachment.getId() > 0) {
					tinhHinhThucHienHopDong.setHoaDonFileDinhKemId(coreAttachment.getId());
					serviceTinhHinhThucHienHopDongService.save(tinhHinhThucHienHopDong);
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
				}

				/* tho??t n???u ????nh k??m 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}
		/* End ????nh k??m file **********************************************************/

		// x??? l?? ????nh k??m HopDongFileDinhKem
		/* Begin ????nh k??m file *******************************************************/

		/*
		 * Kh???i t???o bi???n **************************************************************
		 * - fileDinhKemIds: danh s??ch id file ???? ????nh k??m ****************************
		 * - type: lo???i ????nh k??m (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) *************
		 * - objectId: id ?????i t?????ng ????nh k??m ******************************************
		 * - appCode: t??n model c???a ?????i t?????ng ????nh k??m*********************************
		 */
		fileDinhKemIds = tinhHinhThucHienHopDongInput.getListThanhToanFileDinhKemId();
		type = Constants.DINH_KEM_1_FILE;
		objectId = tinhHinhThucHienHopDong.getId();
		appCode = TinhHinhThucHienHopDong.class.getSimpleName();
		/* X??a m???m ????nh k??m c?? n???u c?? tr?????c khi set file ????nh k??m n???u ????nh k??m nhi???u */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type,
						appCode);

				/* set db n???u c?? tr?????ng l??u v?? chuy???n file t??? temp sang th?? m???c ch??nh */
				if (coreAttachment.getId() > 0) {
					tinhHinhThucHienHopDong.setThanhToanFileDinhKemId(coreAttachment.getId());
					serviceTinhHinhThucHienHopDongService.save(tinhHinhThucHienHopDong);
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
				}

				/* tho??t n???u ????nh k??m 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}
		/* End ????nh k??m file **********************************************************/

		return tinhHinhThucHienHopDong;
	}

	public TinhHinhThucHienHopDong delete(Long hopDongId, Long tinhHinhThucHienId)
			throws EntityNotFoundException {
		Optional<ThongTinHopDong> optionalThongTinHopDong = serviceThongTinHopDongService
				.findById(hopDongId);
		if (!optionalThongTinHopDong.isPresent()) {
			throw new EntityNotFoundException(ThongTinHopDong.class, "id", hopDongId.toString());
		}
		Optional<TinhHinhThucHienHopDong> optionalTinhHinhThucHienHopDong = serviceTinhHinhThucHienHopDongService
				.findById(tinhHinhThucHienId);
		if (!optionalTinhHinhThucHienHopDong.isPresent()) {
			throw new EntityNotFoundException(TinhHinhThucHienHopDong.class, "id", tinhHinhThucHienId.toString());
		}
		TinhHinhThucHienHopDong tinhHinhThucHienHopDong = optionalTinhHinhThucHienHopDong.get();
		tinhHinhThucHienHopDong.setDaXoa(true);
		tinhHinhThucHienHopDong = serviceTinhHinhThucHienHopDongService.save(tinhHinhThucHienHopDong);
		// x??? l?? gi?? tr??? thanh to??n
		ThongTinHopDong thongTinHopDong = optionalThongTinHopDong.get();
		List<TinhHinhThucHienHopDong> listTinhHinhThucHienHopDong = serviceTinhHinhThucHienHopDongService
				.findByHopDongIdAndDaXoaOrderByThanhToanDotDesc(thongTinHopDong.getId(), false);
		Double giaTriConLai = 0D;
		Double tongGiaTriThanhToan = 0D;
		if (listTinhHinhThucHienHopDong != null && !listTinhHinhThucHienHopDong.isEmpty()) {
			for (TinhHinhThucHienHopDong tinhHinhThucHien : listTinhHinhThucHienHopDong) {
				if (tinhHinhThucHienHopDong.getThanhToanGiaTri() != null) {
					tongGiaTriThanhToan += tinhHinhThucHienHopDong.getThanhToanGiaTri();
				}
			}
			if (thongTinHopDong.getGiaTri() != null) {
				giaTriConLai = thongTinHopDong.getGiaTri() - tongGiaTriThanhToan;
			}
			thongTinHopDong.setGiaTriConLai(giaTriConLai);
			if (giaTriConLai != null && giaTriConLai <= 0) {
				thongTinHopDong.setTrangThai(Constants.KHTC_QLHD_TRANG_THAI_DA_THANH_TOAN);
			} else if (giaTriConLai != null && giaTriConLai < thongTinHopDong.getGiaTri()) {
				thongTinHopDong.setTrangThai(Constants.KHTC_QLHD_TRANG_THAI_DANG_THANH_TOAN);
			} else {
				thongTinHopDong.setTrangThai(Constants.KHTC_QLHD_TRANG_THAI_CHUA_THANH_TOAN);
			}
			serviceThongTinHopDongService.save(thongTinHopDong);
		}

		return tinhHinhThucHienHopDong;
	}
}
