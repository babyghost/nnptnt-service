package vn.dnict.microservice.nnptnt.kehoach.data.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.model.DmLoaiNhiemVu;
import vn.dnict.microservice.nnptnt.dm.loainhiemvu.dao.service.DmLoaiNhiemVuService;
import vn.dnict.microservice.nnptnt.kehoach.data.DmLoaiNhiemVuData;

@Component
public class DmLoaiNhiemVuValidator {
	@Autowired
	protected MessageSource messageSource;
	@Autowired
	protected DmLoaiNhiemVuService serviceDmLoaiNhiemVuService;

	public boolean supports(Class<?> clazz) {
		return DmLoaiNhiemVuData.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		DmLoaiNhiemVuData object = (DmLoaiNhiemVuData) target;

		if (checkMaExits(object.getId(), object.getMa())) {
			errors.rejectValue("ma", "error.ma", new Object[] { "ma" }, "Mã đã tồn tại");
		}
	}

	public boolean checkMaExits(Long id, String ma) {
		boolean check = false;
		if (Objects.nonNull(ma) && !ma.isEmpty()) {
			List<DmLoaiNhiemVu> dmLoaiNhiemVus = new ArrayList<>();
			if (id != null && id > 0) {
				dmLoaiNhiemVus = serviceDmLoaiNhiemVuService.findByMaIgnoreCaseAndDaXoa(ma, false);
				if (dmLoaiNhiemVus != null && !dmLoaiNhiemVus.isEmpty()) {
					if (dmLoaiNhiemVus.size() == 1) {
						dmLoaiNhiemVus = serviceDmLoaiNhiemVuService.findByIdAndMaIgnoreCaseAndDaXoa(id, ma, false);
						if (dmLoaiNhiemVus == null || dmLoaiNhiemVus.isEmpty()) {
							check = true;
						}
					} else {
						check = true;
					}
				}
			} else {
				dmLoaiNhiemVus = serviceDmLoaiNhiemVuService.findByMaIgnoreCaseAndDaXoa(ma, false);
				if (dmLoaiNhiemVus != null && !dmLoaiNhiemVus.isEmpty()) {
					check = true;
				}
			}
		}
		return check;
	}
}
