package vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.service;

import java.util.List;
import java.util.Optional;
import vn.dnict.microservice.nnptnt.kehoach.tiendonhiemvunam.dao.model.TienDoNhiemVuNam;

public interface TienDoNhiemVuNamService {
	public TienDoNhiemVuNam save(TienDoNhiemVuNam tienDoNhiemVuNam);

	public void deleteById(Long id);

	public Optional<TienDoNhiemVuNam> findById(Long id);

	public List<TienDoNhiemVuNam> findByNhiemVuNamIdAndDaXoa(Long nhiemVuNamId, Boolean daXoa);

	public int setFixedDaXoaForNhiemVuNamId(Boolean daXoa, Long nhiemVuNamId);
}
