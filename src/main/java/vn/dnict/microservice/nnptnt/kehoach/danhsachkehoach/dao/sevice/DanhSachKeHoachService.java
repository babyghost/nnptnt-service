package vn.dnict.microservice.nnptnt.kehoach.danhsachkehoach.dao.sevice;

import java.util.List;

import vn.dnict.microservice.nnptnt.kehoach.danhsachkehoach.dao.model.DanhSachKeHoach;

public interface DanhSachKeHoachService {

	List<DanhSachKeHoach> findAll(String tenKeHoach, Integer nam, Long phongBanId, Long donViId, Long nguoiNhanId, Integer loaiKeHoach,
			Integer trangThai, Boolean isDieuChinh, Boolean isBanHanh);

}
