package vn.dnict.microservice.nnptnt.baocao.chitietbaocao.dao.service;

import java.util.List;
import java.util.Optional;

import vn.dnict.microservice.nnptnt.baocao.chitietbaocao.dao.model.ChiTietBaoCao;

public interface ChiTietBaoCaoService {

	public ChiTietBaoCao save(ChiTietBaoCao chiTietBaoCao);

	public void deleteById(Long id);

	public boolean existsById(Long id);

	public Optional<ChiTietBaoCao> findById(Long id);

	public int setFixedDaXoaForYeuCauBaoCaoId(boolean daXoa, Long yeuCauBaoCaoId);

	public List<ChiTietBaoCao> findByYeuCauBaoCaoIdAndDonViIdAndDaXoa(Long yeuCauBaoCaoId,Long donViId, boolean daXoa);
	

	public List<ChiTietBaoCao> findByYeuCauBaoCaoIdAndDaXoa(Long yeuCauBaoCaoId, boolean daXoa);
	
}
