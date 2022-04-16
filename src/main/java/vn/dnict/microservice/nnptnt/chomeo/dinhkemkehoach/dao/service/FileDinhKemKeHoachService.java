package vn.dnict.microservice.nnptnt.chomeo.dinhkemkehoach.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.chomeo.dinhkemkehoach.dao.model.FileDinhKemKeHoach;

public interface FileDinhKemKeHoachService {
	public FileDinhKemKeHoach save(FileDinhKemKeHoach fileDinhKemKeHoach);

	public void deleteById(Long id);

	public Optional<FileDinhKemKeHoach> findById(Long id);

	public List<FileDinhKemKeHoach> findByDinhKemFileIdAndDaXoa(Long dinhKemFileId, Boolean daXoa);


	public int setFixedDaXoaForKeHoachTiemPhongId(Boolean daXoa, Long keHoachTiemPhongId);
	public Optional<FileDinhKemKeHoach> findBykeHoachTiemPhongId(Long keHoachTiemPhongId);

	public List<FileDinhKemKeHoach> findByDinhKemFileIdAndKeHoachTiemPhongId(Long dinhKemFileId, Long keHoachTiemPhongId);
}
