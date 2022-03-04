package vn.dnict.microservice.danhmuc.data.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import vn.dnict.microservice.danhmuc.dao.model.DmTinhThanh;
import vn.dnict.microservice.danhmuc.dao.service.DmTinhThanhService;
import vn.dnict.microservice.danhmuc.data.DmTinhThanhData;

@Component
public class DmTinhThanhValidator implements Validator {
	@Autowired
	protected MessageSource messageSource;
	@Autowired
	protected DmTinhThanhService dmTinhThanhService;

	@Override
	public boolean supports(Class<?> clazz) {
		return DmTinhThanhData.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DmTinhThanhData object = (DmTinhThanhData) target;

		if (checkMaExits(object.getId(), object.getMa())) {
			errors.rejectValue("ma", "error.ma", new Object[] { "ma" }, "Mã đã tồn tại");
		}
	}

	public boolean checkMaExits(Long id, String ma) {
		boolean check = false;
		List<DmTinhThanh> listDmTinhThanh = new ArrayList<>();
		if (id != null && id > 0) {
			listDmTinhThanh = dmTinhThanhService.findByMaIgnoreCaseAndDaXoa(ma, false);
			if (listDmTinhThanh != null && !listDmTinhThanh.isEmpty()) {
				if (listDmTinhThanh.size() == 1) {
					listDmTinhThanh = dmTinhThanhService.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, false);
					if (listDmTinhThanh == null || listDmTinhThanh.isEmpty()) {
						check = true;
					}
				} else {
					check = true;
				}
			}
		} else {
			listDmTinhThanh = dmTinhThanhService.findByMaIgnoreCaseAndDaXoa(ma, false);
			if (listDmTinhThanh != null && !listDmTinhThanh.isEmpty()) {
				check = true;
			}
		}
		return check;
	}
}
