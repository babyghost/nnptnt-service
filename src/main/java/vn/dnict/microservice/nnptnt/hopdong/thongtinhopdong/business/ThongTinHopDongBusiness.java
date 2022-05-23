package vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.business;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import vn.dnict.microservice.core.business.CoreAttachmentBusiness;
import vn.dnict.microservice.core.dao.model.CoreAttachment;
import vn.dnict.microservice.core.data.FileDinhKem;
import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.chomeo.dinhkemkehoach.dao.model.FileDinhKemKeHoach;
import vn.dnict.microservice.nnptnt.chomeo.kehoachtiemphong.dao.model.KeHoachTiemPhong;
import vn.dnict.microservice.nnptnt.hopdong.data.ThongTinHopDongInput;
import vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.model.ThongTinHopDong;
import vn.dnict.microservice.nnptnt.hopdong.thongtinhopdong.dao.service.ThongTinHopDongService;
import vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.model.TinhHinhThucHienHopDong;
import vn.dnict.microservice.nnptnt.hopdong.tinhhinhthuchienhopdong.dao.service.TinhHinhThucHienHopDongService;
import vn.dnict.microservice.utils.Constants;

@Service
@Slf4j
public class ThongTinHopDongBusiness {
	@Autowired
	private ThongTinHopDongService serviceThongTinHopDongService;
	@Autowired
	private CoreAttachmentBusiness coreAttachmentBusiness;
	@Autowired
	private TinhHinhThucHienHopDongService serviceTinhHinhThucHienHopDongService;

	public Page<ThongTinHopDong> findAll(int page, int size, String sortBy, String sortDir, String search,
			String ten, String soHieu, Long loaiHopDongId, Integer trangThai, LocalDate tuNgayKy, LocalDate denNgayKy,
			LocalDate thoiGianThTuNgay, LocalDate thoiGianThDenNgay) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<ThongTinHopDong> pageThongTinHopDong = serviceThongTinHopDongService.findAll(search, ten,
				soHieu, loaiHopDongId, trangThai, tuNgayKy, denNgayKy, thoiGianThTuNgay, thoiGianThDenNgay,
				PageRequest.of(page, size, direction, sortBy));
		return pageThongTinHopDong;
	}

	public long countHopDongSapHetHan() {
		long count = 0;
		LocalDate nowDay = LocalDate.now();
		LocalDate before5day = nowDay.minusDays(5);
		Long countHopDong = serviceThongTinHopDongService.countByTrangThaiAndThoiGianThDenNgayBetween(
				Constants.KHTC_QLHD_TRANG_THAI_DANG_THANH_TOAN, before5day, nowDay);
		if (countHopDong != null) {
			count = countHopDong;
		}
		return count;
	}

	public ThongTinHopDongInput findById(Long id) throws EntityNotFoundException {
		Optional<ThongTinHopDong> optional = serviceThongTinHopDongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinHopDong.class, "id", id.toString());
		}
		ThongTinHopDongInput thongTinHopDongInput = new ThongTinHopDongInput();
		ThongTinHopDong thongTinHopDong = optional.get();
		thongTinHopDongInput.setTen(thongTinHopDong.getTen());
		thongTinHopDongInput.setSoHieu(thongTinHopDong.getSoHieu());
		thongTinHopDongInput.setNgayKy(thongTinHopDong.getNgayKy());
		thongTinHopDongInput.setGiaTri(thongTinHopDong.getGiaTri());
		thongTinHopDongInput.setGiaTriConLai(thongTinHopDong.getGiaTriConLai());
		thongTinHopDongInput.setLoaiHopDongId(thongTinHopDong.getLoaiHopDongId());
		thongTinHopDongInput.setDvthTen(thongTinHopDong.getDvthTen());
		thongTinHopDongInput.setDvthMaSoThue(thongTinHopDong.getDvthMaSoThue());
		thongTinHopDongInput.setDvthDiaChi(thongTinHopDong.getDvthDiaChi());
		thongTinHopDongInput.setPhongBanTheoDoiId(thongTinHopDong.getPhongBanTheoDoiId());
		thongTinHopDongInput.setCnthTen(thongTinHopDong.getCnthTen());
		thongTinHopDongInput.setCnthMaSoThue(thongTinHopDong.getCnthMaSoThue());
		thongTinHopDongInput.setCnthDiaChi(thongTinHopDong.getCnthDiaChi());
		thongTinHopDongInput.setThoiGianThTuNgay(thongTinHopDong.getThoiGianThTuNgay());
		thongTinHopDongInput.setThoiGianThDenNgay(thongTinHopDong.getThoiGianThDenNgay());
		thongTinHopDongInput.setThoiGianBhTuNgay(thongTinHopDong.getThoiGianBhTuNgay());
		thongTinHopDongInput.setThoiGianBhDenNgay(thongTinHopDong.getThoiGianBhDenNgay());
		thongTinHopDongInput.setNguoiKyId(thongTinHopDong.getNguoiKyId());
		thongTinHopDongInput.setGiayUyQuyenSo(thongTinHopDong.getGiayUyQuyenSo());
		thongTinHopDongInput.setGiayUyQuyenNgay(thongTinHopDong.getGiayUyQuyenNgay());
		thongTinHopDongInput.setBaoLanhThucHienId(thongTinHopDong.getBaoLanhThucHienId());
		thongTinHopDongInput.setBaoLanhThucHienGiaTri(thongTinHopDong.getBaoLanhThucHienGiaTri());
		thongTinHopDongInput.setBaoLanhThucHienTuNgay(thongTinHopDong.getBaoLanhThucHienTuNgay());
		thongTinHopDongInput.setBaoLanhThucHienDenNgay(thongTinHopDong.getBaoLanhThucHienDenNgay());
		thongTinHopDongInput.setBaoLanhBaoHanhId(thongTinHopDong.getBaoLanhBaoHanhId());
		thongTinHopDongInput.setBaoLanhBaoHanhGiaTri(thongTinHopDong.getBaoLanhBaoHanhGiaTri());
		thongTinHopDongInput.setBaoLanhBaoHanhTuNgay(thongTinHopDong.getBaoLanhBaoHanhTuNgay());
		thongTinHopDongInput.setBaoLanhBaoHanhDenNgay(thongTinHopDong.getBaoLanhBaoHanhDenNgay());
		thongTinHopDongInput
				.setBaoLanhBaoHanhPhuongThucHoanTra(thongTinHopDong.getBaoLanhBaoHanhPhuongThucHoanTra());
		thongTinHopDongInput.setBaoLanhBaoHanhNguoiNhan(thongTinHopDong.getBaoLanhBaoHanhNguoiNhan());
		thongTinHopDongInput.setCoCamKetChi(thongTinHopDong.getCoCamKetChi());
		thongTinHopDongInput.setCoCamKetChiSo(thongTinHopDong.getCoCamKetChiSo());
		thongTinHopDongInput.setCoCamKetNgay(thongTinHopDong.getCoCamKetNgay());
		thongTinHopDongInput.setTrangThai(thongTinHopDong.getTrangThai());
		
		if (Objects.nonNull(thongTinHopDong)) {
			int type = Constants.DINH_KEM_1_FILE;			
			Long fileDinhKemId = null;
			Long objectId = thongTinHopDong.getId();
			String appCode = ThongTinHopDong.class.getSimpleName();
			FileDinhKem fileDinhKem = coreAttachmentBusiness.getAttachments( thongTinHopDong.getHopDongFileDinhKemId(), appCode, objectId, type);
			thongTinHopDongInput.setListHopDongFileDinhKemId(fileDinhKem.getIds());
			thongTinHopDongInput.setListHopDongFileDinhKem(fileDinhKem.getFileLists());
		}
		if (Objects.nonNull(thongTinHopDong)) {
			int type = Constants.DINH_KEM_1_FILE;			
			Long fileDinhKemId = null;
			Long objectId = thongTinHopDong.getId();
			String appCode = ThongTinHopDong.class.getSimpleName();
			FileDinhKem fileDinhKems = coreAttachmentBusiness.getAttachments( thongTinHopDong.getGiayUyQuyenFileDinhKemId(), appCode, objectId, type);
			thongTinHopDongInput.setListGiayUyQuyenFileDinhKemId(fileDinhKems.getIds());
			thongTinHopDongInput.setListGiayUyQuyenFileDinhKem(fileDinhKems.getFileLists());
		}
		if (Objects.nonNull(thongTinHopDong)) {
			int type = Constants.DINH_KEM_1_FILE;			
			Long fileDinhKemId = null;
			Long objectId = thongTinHopDong.getId();
			String appCode = ThongTinHopDong.class.getSimpleName();
			FileDinhKem fileDinhKemss = coreAttachmentBusiness.getAttachments( thongTinHopDong.getCoCamKetChiFileDinhKemId(), appCode, objectId, type);
			thongTinHopDongInput.setListCoCamKetChiFileDinhKemId(fileDinhKemss.getIds());
			thongTinHopDongInput.setListCoCamKetChiFileDinhKem(fileDinhKemss.getFileLists());
		}
		return thongTinHopDongInput;
	}

	public FileDinhKem getFileDinhKemGiayUyQuyen(Long id) throws EntityNotFoundException {
		Optional<ThongTinHopDong> optional = serviceThongTinHopDongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinHopDong.class, "id", id.toString());
		}
		int type = Constants.DINH_KEM_1_FILE;
		Long fileDinhKemId = optional.get().getGiayUyQuyenFileDinhKemId();
		Long objectId = optional.get().getId();
		String appCode = ThongTinHopDong.class.getSimpleName();
		return coreAttachmentBusiness.getAttachments(fileDinhKemId, appCode, objectId, type);
	}

	public FileDinhKem getFileDinhKemHopDong(Long id) throws EntityNotFoundException {
		Optional<ThongTinHopDong> optional = serviceThongTinHopDongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinHopDong.class, "id", id.toString());
		}
		int type = Constants.DINH_KEM_NHIEU_FILE;
		Long fileDinhKemId = optional.get().getHopDongFileDinhKemId();
		Long objectId = optional.get().getId();
		String appCode = ThongTinHopDong.class.getSimpleName();
		return coreAttachmentBusiness.getAttachments(fileDinhKemId, appCode, objectId, type);
	}

	public FileDinhKem getFileDinhKemCoCamKetChi(Long id) throws EntityNotFoundException {
		Optional<ThongTinHopDong> optional = serviceThongTinHopDongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinHopDong.class, "id", id.toString());
		}
		int type = Constants.DINH_KEM_1_FILE;
		Long fileDinhKemId = optional.get().getCoCamKetChiFileDinhKemId();
		Long objectId = optional.get().getId();
		String appCode = ThongTinHopDong.class.getSimpleName();
		return coreAttachmentBusiness.getAttachments(fileDinhKemId, appCode, objectId, type);
	}

	public ThongTinHopDong create(ThongTinHopDongInput thongTinHopDongInput) {
		ThongTinHopDong thongTinHopDong = new ThongTinHopDong();
		thongTinHopDong.setDaXoa(false);
		thongTinHopDong.setTen(thongTinHopDongInput.getTen());
		thongTinHopDong.setSoHieu(thongTinHopDongInput.getSoHieu());
		thongTinHopDong.setNgayKy(thongTinHopDongInput.getNgayKy());
		thongTinHopDong.setGiaTri(thongTinHopDongInput.getGiaTri());
		thongTinHopDong.setGiaTriConLai(thongTinHopDongInput.getGiaTriConLai());
		thongTinHopDong.setLoaiHopDongId(thongTinHopDongInput.getLoaiHopDongId());
		thongTinHopDong.setDvthTen(thongTinHopDongInput.getDvthTen());
		thongTinHopDong.setDvthMaSoThue(thongTinHopDongInput.getDvthMaSoThue());
		thongTinHopDong.setDvthDiaChi(thongTinHopDongInput.getDvthDiaChi());
		thongTinHopDong.setPhongBanTheoDoiId(thongTinHopDongInput.getPhongBanTheoDoiId());
		thongTinHopDong.setCnthTen(thongTinHopDongInput.getCnthTen());
		thongTinHopDong.setCnthMaSoThue(thongTinHopDongInput.getCnthMaSoThue());
		thongTinHopDong.setCnthDiaChi(thongTinHopDongInput.getCnthDiaChi());
		thongTinHopDong.setThoiGianThTuNgay(thongTinHopDongInput.getThoiGianThTuNgay());
		thongTinHopDong.setThoiGianThDenNgay(thongTinHopDongInput.getThoiGianThDenNgay());
		thongTinHopDong.setThoiGianBhTuNgay(thongTinHopDongInput.getThoiGianBhTuNgay());
		thongTinHopDong.setThoiGianBhDenNgay(thongTinHopDongInput.getThoiGianBhDenNgay());
		thongTinHopDong.setNguoiKyId(thongTinHopDongInput.getNguoiKyId());
		thongTinHopDong.setGiayUyQuyenSo(thongTinHopDongInput.getGiayUyQuyenSo());
		thongTinHopDong.setGiayUyQuyenNgay(thongTinHopDongInput.getGiayUyQuyenNgay());
		thongTinHopDong.setBaoLanhThucHienId(thongTinHopDongInput.getBaoLanhThucHienId());
		thongTinHopDong.setBaoLanhThucHienGiaTri(thongTinHopDongInput.getBaoLanhThucHienGiaTri());
		thongTinHopDong.setBaoLanhThucHienTuNgay(thongTinHopDongInput.getBaoLanhThucHienTuNgay());
		thongTinHopDong.setBaoLanhThucHienDenNgay(thongTinHopDongInput.getBaoLanhThucHienDenNgay());
		thongTinHopDong.setBaoLanhBaoHanhId(thongTinHopDongInput.getBaoLanhBaoHanhId());
		thongTinHopDong.setBaoLanhBaoHanhGiaTri(thongTinHopDongInput.getBaoLanhBaoHanhGiaTri());
		thongTinHopDong.setBaoLanhBaoHanhTuNgay(thongTinHopDongInput.getBaoLanhBaoHanhTuNgay());
		thongTinHopDong.setBaoLanhBaoHanhDenNgay(thongTinHopDongInput.getBaoLanhBaoHanhDenNgay());
		thongTinHopDong
				.setBaoLanhBaoHanhPhuongThucHoanTra(thongTinHopDongInput.getBaoLanhBaoHanhPhuongThucHoanTra());
		thongTinHopDong.setBaoLanhBaoHanhNguoiNhan(thongTinHopDongInput.getBaoLanhBaoHanhNguoiNhan());
		thongTinHopDong.setCoCamKetChi(thongTinHopDongInput.getCoCamKetChi());
		thongTinHopDong.setCoCamKetChiSo(thongTinHopDongInput.getCoCamKetChiSo());
		thongTinHopDong.setCoCamKetNgay(thongTinHopDongInput.getCoCamKetNgay());

		thongTinHopDong.setTrangThai(Constants.KHTC_QLHD_TRANG_THAI_CHUA_THANH_TOAN);
		thongTinHopDong = serviceThongTinHopDongService.save(thongTinHopDong);
		// xử lý đính kèm GiayUyQuyenFileDinhKem
		/* Begin đính kèm file *******************************************************/

		/*
		 * Khởi tạo biến **************************************************************
		 * - fileDinhKemIds: danh sách id file đã đính kèm ****************************
		 * - type: loại đính kèm (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) *************
		 * - objectId: id đối tượng đính kèm ******************************************
		 * - appCode: tên model của đối tượng đính kèm*********************************
		 */
		List<Long> fileDinhKemIds = thongTinHopDongInput.getListGiayUyQuyenFileDinhKemId();
		int type = Constants.DINH_KEM_1_FILE;
		long objectId = thongTinHopDong.getId();
		String appCode = ThongTinHopDong.class.getSimpleName();
		/* Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính kèm nhiều */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type,
						appCode);

				/* set db nếu có trường lưu và chuyển file từ temp sang thư mục chính */
				if (coreAttachment.getId() > 0) {
					thongTinHopDong.setGiayUyQuyenFileDinhKemId(coreAttachment.getId());
					serviceThongTinHopDongService.save(thongTinHopDong);
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
				}

				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}
		/* End đính kèm file **********************************************************/

		// xử lý đính kèm HopDongFileDinhKem nhiều file
		/* Begin đính kèm file *******************************************************/

		/*
		 * Khởi tạo biến **************************************************************
		 * - fileDinhKemIds: danh sách id file đã đính kèm ****************************
		 * - type: loại đính kèm (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) *************
		 * - objectId: id đối tượng đính kèm ******************************************
		 * - appCode: tên model của đối tượng đính kèm*********************************
		 */
		fileDinhKemIds = thongTinHopDongInput.getListHopDongFileDinhKemId();
		type = Constants.DINH_KEM_1_FILE;
		objectId = thongTinHopDong.getId();
		appCode = ThongTinHopDong.class.getSimpleName();
		/* Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính kèm nhiều */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type,
						appCode);

				/* set db nếu có trường lưu và chuyển file từ temp sang thư mục chính */
				if (coreAttachment.getId() > 0) {
					thongTinHopDong.setHopDongFileDinhKemId(coreAttachment.getId());
					serviceThongTinHopDongService.save(thongTinHopDong);
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
				}

				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}
		/* End đính kèm file **********************************************************/

		// xử lý đính kèm camKEt
		/* Begin đính kèm file *******************************************************/

		/*
		 * Khởi tạo biến **************************************************************
		 * - fileDinhKemIds: danh sách id file đã đính kèm ****************************
		 * - type: loại đính kèm (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) *************
		 * - objectId: id đối tượng đính kèm ******************************************
		 * - appCode: tên model của đối tượng đính kèm*********************************
		 */
		fileDinhKemIds = thongTinHopDongInput.getListCoCamKetChiFileDinhKemId();
		type = Constants.DINH_KEM_1_FILE;
		objectId = thongTinHopDong.getId();
		appCode = ThongTinHopDong.class.getSimpleName();
		/* Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính kèm nhiều */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type,
						appCode);

				/* set db nếu có trường lưu và chuyển file từ temp sang thư mục chính */
				if (coreAttachment.getId() > 0) {
					thongTinHopDong.setCoCamKetChiFileDinhKemId(coreAttachment.getId());
					serviceThongTinHopDongService.save(thongTinHopDong);
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
				}

				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}
		/* End đính kèm file **********************************************************/

		// xử lý đính kèm nghiemthu_filedinhkemid
		/* Begin đính kèm file *******************************************************/

		/*
		 * Khởi tạo biến **************************************************************
		 * - fileDinhKemIds: danh sách id file đã đính kèm ****************************
		 * - type: loại đính kèm (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) *************
		 * - objectId: id đối tượng đính kèm ******************************************
		 * - appCode: tên model của đối tượng đính kèm*********************************
		 */

		/* End đính kèm file **********************************************************/

		// xử lý đính kèm phuluc_filedinhkemid
		/* Begin đính kèm file *******************************************************/

		/*
		 * Khởi tạo biến **************************************************************
		 * - fileDinhKemIds: danh sách id file đã đính kèm ****************************
		 * - type: loại đính kèm (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) *************
		 * - objectId: id đối tượng đính kèm ******************************************
		 * - appCode: tên model của đối tượng đính kèm*********************************
		 */

		/* End đính kèm file **********************************************************/
		return thongTinHopDong;
	}

	public ThongTinHopDong update(Long id, ThongTinHopDongInput thongTinHopDongInput)
			throws EntityNotFoundException {
		Optional<ThongTinHopDong> optional = serviceThongTinHopDongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinHopDong.class, "id", id.toString());
		}
		ThongTinHopDong thongTinHopDong = optional.get();
		thongTinHopDong.setDaXoa(false);
		thongTinHopDong.setTen(thongTinHopDongInput.getTen());
		thongTinHopDong.setSoHieu(thongTinHopDongInput.getSoHieu());
		thongTinHopDong.setNgayKy(thongTinHopDongInput.getNgayKy());
		thongTinHopDong.setGiaTri(thongTinHopDongInput.getGiaTri());
		thongTinHopDong.setLoaiHopDongId(thongTinHopDongInput.getLoaiHopDongId());
		thongTinHopDong.setDvthTen(thongTinHopDongInput.getDvthTen());
		thongTinHopDong.setDvthMaSoThue(thongTinHopDongInput.getDvthMaSoThue());
		thongTinHopDong.setDvthDiaChi(thongTinHopDongInput.getDvthDiaChi());
		thongTinHopDong.setPhongBanTheoDoiId(thongTinHopDongInput.getPhongBanTheoDoiId());
		thongTinHopDong.setCnthTen(thongTinHopDongInput.getCnthTen());
		thongTinHopDong.setCnthMaSoThue(thongTinHopDongInput.getCnthMaSoThue());
		thongTinHopDong.setCnthDiaChi(thongTinHopDongInput.getCnthDiaChi());
		thongTinHopDong.setThoiGianThTuNgay(thongTinHopDongInput.getThoiGianThTuNgay());
		thongTinHopDong.setThoiGianThDenNgay(thongTinHopDongInput.getThoiGianThDenNgay());
		thongTinHopDong.setThoiGianBhTuNgay(thongTinHopDongInput.getThoiGianBhTuNgay());
		thongTinHopDong.setThoiGianBhDenNgay(thongTinHopDongInput.getThoiGianBhDenNgay());
		thongTinHopDong.setNguoiKyId(thongTinHopDongInput.getNguoiKyId());
		thongTinHopDong.setGiayUyQuyenSo(thongTinHopDongInput.getGiayUyQuyenSo());
		thongTinHopDong.setGiayUyQuyenNgay(thongTinHopDongInput.getGiayUyQuyenNgay());
		thongTinHopDong.setBaoLanhThucHienId(thongTinHopDongInput.getBaoLanhThucHienId());
		thongTinHopDong.setBaoLanhThucHienGiaTri(thongTinHopDongInput.getBaoLanhThucHienGiaTri());
		thongTinHopDong.setBaoLanhThucHienTuNgay(thongTinHopDongInput.getBaoLanhThucHienTuNgay());
		thongTinHopDong.setBaoLanhThucHienDenNgay(thongTinHopDongInput.getBaoLanhThucHienDenNgay());
		thongTinHopDong.setBaoLanhBaoHanhId(thongTinHopDongInput.getBaoLanhBaoHanhId());
		thongTinHopDong.setBaoLanhBaoHanhGiaTri(thongTinHopDongInput.getBaoLanhBaoHanhGiaTri());
		thongTinHopDong.setBaoLanhBaoHanhTuNgay(thongTinHopDongInput.getBaoLanhBaoHanhTuNgay());
		thongTinHopDong.setBaoLanhBaoHanhDenNgay(thongTinHopDongInput.getBaoLanhBaoHanhDenNgay());
		thongTinHopDong.setBaoLanhBaoHanhPhuongThucHoanTra(thongTinHopDongInput.getBaoLanhBaoHanhPhuongThucHoanTra());
		thongTinHopDong.setBaoLanhBaoHanhNguoiNhan(thongTinHopDongInput.getBaoLanhBaoHanhNguoiNhan());
		thongTinHopDong.setCoCamKetChi(thongTinHopDongInput.getCoCamKetChi());
		thongTinHopDong.setCoCamKetChiSo(thongTinHopDongInput.getCoCamKetChiSo());
		thongTinHopDong.setCoCamKetNgay(thongTinHopDongInput.getCoCamKetNgay());

		thongTinHopDong = serviceThongTinHopDongService.save(thongTinHopDong);
		// xử lý giá trị thanh toán
		List<TinhHinhThucHienHopDong> listTinhHinhThucHienHopDong = serviceTinhHinhThucHienHopDongService
				.findByHopDongIdAndDaXoaOrderByThanhToanDotDesc(thongTinHopDong.getId(), false);
		Double giaTriConLai = 0D;
		Double tongGiaTriThanhToan = 0D;
		if (listTinhHinhThucHienHopDong != null && !listTinhHinhThucHienHopDong.isEmpty()) {
			for (TinhHinhThucHienHopDong tinhHinhThucHienHopDong : listTinhHinhThucHienHopDong) {
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

		// xử lý đính kèm GiayUyQuyenFileDinhKem
		/* Begin đính kèm file *******************************************************/

		/*
		 * Khởi tạo biến **************************************************************
		 * - fileDinhKemIds: danh sách id file đã đính kèm ****************************
		 * - type: loại đính kèm (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) *************
		 * - objectId: id đối tượng đính kèm ******************************************
		 * - appCode: tên model của đối tượng đính kèm*********************************
		 */
		List<Long> fileDinhKemIds = thongTinHopDongInput.getListGiayUyQuyenFileDinhKemId();
		int type = Constants.DINH_KEM_1_FILE;
		long objectId = thongTinHopDong.getId();
		String appCode = ThongTinHopDong.class.getSimpleName();
		/* Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính kèm nhiều */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type,
						appCode);
			
				/* set db nếu có trường lưu và chuyển file từ temp sang thư mục chính */
				if (coreAttachment.getId() > 0) {
					thongTinHopDong.setGiayUyQuyenFileDinhKemId(coreAttachment.getId());
					thongTinHopDongInput.setListGiayUyQuyenFileDinhKemId(fileDinhKemIds);
					serviceThongTinHopDongService.save(thongTinHopDong);
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
				}

				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}
		/* End đính kèm file **********************************************************/

		// xử lý đính kèm HopDongFileDinhKem nhiều file
		/* Begin đính kèm file *******************************************************/

		/*
		 * Khởi tạo biến **************************************************************
		 * - fileDinhKemIds: danh sách id file đã đính kèm ****************************
		 * - type: loại đính kèm (DINH_KEM_1_FILE || DINH_KEM_NHIEU_FILE) *************
		 * - objectId: id đối tượng đính kèm ******************************************
		 * - appCode: tên model của đối tượng đính kèm*********************************
		 */
		fileDinhKemIds = thongTinHopDongInput.getListHopDongFileDinhKemId();
		type = Constants.DINH_KEM_1_FILE;
		objectId = thongTinHopDong.getId();
		appCode = ThongTinHopDong.class.getSimpleName();
		/* Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính kèm nhiều */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type,
						appCode);
				/* set db nếu có trường lưu và chuyển file từ temp sang thư mục chính */
				if (coreAttachment.getId() > 0) {
					thongTinHopDong.setHopDongFileDinhKemId(coreAttachment.getId());
					thongTinHopDongInput.setListHopDongFileDinhKemId(fileDinhKemIds);
					serviceThongTinHopDongService.save(thongTinHopDong);
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
				}

				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}

		fileDinhKemIds = thongTinHopDongInput.getListCoCamKetChiFileDinhKemId();
		type = Constants.DINH_KEM_1_FILE;
		objectId = thongTinHopDong.getId();
		appCode = ThongTinHopDong.class.getSimpleName();
		/* Xóa mềm đính kèm cũ nếu có trước khi set file đính kèm nếu đính kèm nhiều */
		if (type == Constants.DINH_KEM_NHIEU_FILE) {
			coreAttachmentBusiness.setFixDaXoaByObjectIdAndAppCodeAndType(objectId, appCode, type);
		}
		if (Objects.nonNull(fileDinhKemIds) && !fileDinhKemIds.isEmpty()) {
			for (Long fileDinhKemId : fileDinhKemIds) {
				CoreAttachment coreAttachment = coreAttachmentBusiness.dinhKemFile(fileDinhKemId, objectId, type,
						appCode);

				/* set db nếu có trường lưu và chuyển file từ temp sang thư mục chính */
				if (coreAttachment.getId() > 0) {
					thongTinHopDong.setCoCamKetChiFileDinhKemId(coreAttachment.getId());
					thongTinHopDongInput.setListCoCamKetChiFileDinhKemId(fileDinhKemIds);
					serviceThongTinHopDongService.save(thongTinHopDong);
					coreAttachmentBusiness.saveAndMove(coreAttachment);
					log.info("coreAttachment id :" + coreAttachment.getId());
				}

				/* thoát nếu đính kèm 1 file */
				if (type == Constants.DINH_KEM_1_FILE) {
					break;
				}
			}
		}

		return thongTinHopDong;
	}

	public ThongTinHopDong delete(Long id) throws EntityNotFoundException {
		Optional<ThongTinHopDong> optional = serviceThongTinHopDongService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(ThongTinHopDong.class, "id", id.toString());
		}
		ThongTinHopDong ThongTinHopDong = optional.get();
		ThongTinHopDong.setDaXoa(true);
		ThongTinHopDong = serviceThongTinHopDongService.save(ThongTinHopDong);
		return ThongTinHopDong;
	}
}
