package vn.dnict.microservice.nnptnt.kehoach.data.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import vn.dnict.microservice.nnptnt.kehoach.danhmuc.loainhiemvu.dao.model.DmLoaiNhiemVu;
import vn.dnict.microservice.nnptnt.kehoach.danhmuc.loainhiemvu.dao.service.DmLoaiNhiemVuService;
import vn.dnict.microservice.nnptnt.kehoach.data.DmLoaiNhiemVuData;

@Component
public class DmLoaiNhiemVuValidator implements Validator {
	@Autowired
	protected MessageSource messageSource;
	@Autowired
	protected DmLoaiNhiemVuService serviceDmLoaiNhiemVuService;

	@Override
	public boolean supports(Class<?> clazz) {
		return DmLoaiNhiemVuData.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DmLoaiNhiemVuData object = (DmLoaiNhiemVuData) target;

		if (checkMaExits(object.getId(), object.getMa())) {
			// errors.rejectValue("ma", "error.ma", new Object[]{"ma"}, "Mã đã tồn tại");
		}
	}

	public boolean checkMaExits(Long id, String ma) {
		boolean check = false;
		List<DmLoaiNhiemVu> khDmLoaiNhiemVus = new ArrayList<>();
		if (id != null && id > 0) {
			khDmLoaiNhiemVus = serviceDmLoaiNhiemVuService.findByMaIgnoreCaseAndDaXoa(ma, false);
			if (khDmLoaiNhiemVus != null && !khDmLoaiNhiemVus.isEmpty()) {
				if (khDmLoaiNhiemVus.size() == 1) {
					khDmLoaiNhiemVus = serviceDmLoaiNhiemVuService.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, false);
					if (khDmLoaiNhiemVus == null || khDmLoaiNhiemVus.isEmpty()) {
						check = true;
					}
				} else {
					check = true;
				}
			}
		} else {
			khDmLoaiNhiemVus = serviceDmLoaiNhiemVuService.findByMaIgnoreCaseAndDaXoa(ma, false);
			if (khDmLoaiNhiemVus != null && !khDmLoaiNhiemVus.isEmpty()) {
				check = true;
			}
		}
		return check;
	}
}
