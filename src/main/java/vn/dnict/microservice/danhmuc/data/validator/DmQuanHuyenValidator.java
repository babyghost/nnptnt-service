package vn.dnict.microservice.danhmuc.data.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import vn.dnict.microservice.danhmuc.dao.model.DmQuanHuyen;
import vn.dnict.microservice.danhmuc.dao.service.DmQuanHuyenService;
import vn.dnict.microservice.danhmuc.data.DmQuanHuyenData;

@Component
public class DmQuanHuyenValidator implements Validator {
	@Autowired
	protected MessageSource messageSource;
	@Autowired
	protected DmQuanHuyenService dmQuanHuyenService;

	@Override
	public boolean supports(Class<?> clazz) {
		return DmQuanHuyenData.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DmQuanHuyenData object = (DmQuanHuyenData) target;

		if (checkMaExits(object.getId(), object.getMa())) {
			errors.rejectValue("ma", "error.ma", new Object[] { "ma" }, "Mã đã tồn tại");
		}
	}

	public boolean checkMaExits(Long id, String ma) {
		boolean check = false;
		List<DmQuanHuyen> listDmQuanHuyen = new ArrayList<>();
		if (id != null && id > 0) {
			listDmQuanHuyen = dmQuanHuyenService.findByMaIgnoreCaseAndDaXoa(ma, false);
			if (listDmQuanHuyen != null && !listDmQuanHuyen.isEmpty()) {
				if (listDmQuanHuyen.size() == 1) {
					listDmQuanHuyen = dmQuanHuyenService.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, false);
					if (listDmQuanHuyen == null || listDmQuanHuyen.isEmpty()) {
						check = true;
					}
				} else {
					check = true;
				}
			}
		} else {
			listDmQuanHuyen = dmQuanHuyenService.findByMaIgnoreCaseAndDaXoa(ma, false);
			if (listDmQuanHuyen != null && !listDmQuanHuyen.isEmpty()) {
				check = true;
			}
		}
		return check;
	}
}
