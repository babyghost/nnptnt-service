package vn.dnict.microservice.nnptnt.qlchomeo.dao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.dnict.microservice.nnptnt.qlchomeo.dao.model.FileDinhKemKeHoach;

public interface FileDinhKemKeHoachService {

	public Page<FileDinhKemKeHoach> findAll(String search, Long fileDinhKemId, Long keHoachTiemPhongId, Pageable pageable);

	public Optional<FileDinhKemKeHoach> findByKeHoachTiemPhongId(Long keHoachTiemPhongId);
}
