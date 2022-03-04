package vn.dnict.microservice.danhmuc.data.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import vn.dnict.microservice.danhmuc.dao.model.DmPhuongXa;
import vn.dnict.microservice.danhmuc.dao.service.DmPhuongXaService;
import vn.dnict.microservice.danhmuc.data.DmPhuongXaData;

@Component
public class DmPhuongXaValidator implements Validator {
	@Autowired
	protected MessageSource messageSource;
	@Autowired
	protected DmPhuongXaService dmPhuongXaService;

	@Override
	public boolean supports(Class<?> clazz) {
		return DmPhuongXaData.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DmPhuongXaData object = (DmPhuongXaData) target;

		if (checkMaExits(object.getId(), object.getMa())) {
			errors.rejectValue("ma", "error.ma", new Object[] { "ma" }, "Mã đã tồn tại");
		}
	}

	public boolean checkMaExits(Long id, String ma) {
		boolean check = false;
		List<DmPhuongXa> listDmPhuongXa = new ArrayList<>();
		if (id != null && id > 0) {
			listDmPhuongXa = dmPhuongXaService.findByMaIgnoreCaseAndDaXoa(ma, false);
			if (listDmPhuongXa != null && !listDmPhuongXa.isEmpty()) {
				if (listDmPhuongXa.size() == 1) {
					listDmPhuongXa = dmPhuongXaService.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, false);
					if (listDmPhuongXa == null || listDmPhuongXa.isEmpty()) {
						check = true;
					}
				} else {
					check = true;
				}
			}
		} else {
			listDmPhuongXa = dmPhuongXaService.findByMaIgnoreCaseAndDaXoa(ma, false);
			if (listDmPhuongXa != null && !listDmPhuongXa.isEmpty()) {
				check = true;
			}
		}
		return check;
	}
}
