package vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.kehoach.kehoachnam.dao.model.KeHoachNam;

public interface KeHoachNamService {
	public KeHoachNam save(KeHoachNam keHoachNam);

	public void deleteById(Long id);

	public boolean existsById(Long id);

	public Optional<KeHoachNam> findById(Long id);

	public Page<KeHoachNam> findAll(Long donViChuTriId, Integer nam, String tenKeHoach, String soKyHieu, Boolean trangThai,
			LocalDate tuNgayBanHanh, LocalDate denNgayBanHanh, Pageable pageable);
}
