package vn.dnict.microservice.danhmuc.dao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.dnict.microservice.danhmuc.dao.model.DmDonVi;

@Service
@Transactional
public class DmDonViServiceImpl implements DmDonViService {
	@Autowired
	private DmDonViRepo dmDonViRepo;
	
	public DmDonVi save(DmDonVi DmDonVi) {
		return dmDonViRepo.save(DmDonVi);
	}

	public Optional<DmDonVi> findById(Long id) {
		return dmDonViRepo.findById(id);
	}

	public void delete(Long id) {
		dmDonViRepo.deleteById(id);
	}

	public Page<DmDonVi> findAll(String search, String tenDmDonVi, String diaChi, String soDienThoai, String email, Long DmDonViChaId, Long capId, Long loaiDmDonViId, Pageable pageable) {
		return dmDonViRepo.findAll(DmDonViSpecifications.quickSearch(search, tenDmDonVi, diaChi, soDienThoai, email, DmDonViChaId, capId, loaiDmDonViId), pageable);
	}

	public Page<DmDonVi> findByDaXoaAndSearch(Boolean daXoa, String search, Pageable pageable) {
		return dmDonViRepo.findAll(DmDonViSpecifications.findByDaXoaAndSearch(daXoa, search), pageable);
	}

	public Page<DmDonVi> findByDaXoaAndTrangThaiAndSearch(Boolean daXoa, Integer trangThai, String search,
			Pageable pageable) {
		return dmDonViRepo.findAll(DmDonViSpecifications.findByDaXoaAndTrangThaiAndSearch(daXoa, trangThai, search),
				pageable);
	}

	public List<DmDonVi> findByIdIn(List<Long> idList) {
		return dmDonViRepo.findByIdIn(idList);
	}

	public List<DmDonVi> listCha() {
		return dmDonViRepo.findByDaXoa(false);
	}

	public List<DmDonVi> listCha(List<DmDonVi> list, List<DmDonVi> listKetQua, String hienThi) {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) != null) {
					list.get(i).setTenDonVi(hienThi + list.get(i).getTenDonVi());
					listKetQua.add(list.get(i));
					List<DmDonVi> listDmDonViCha = dmDonViRepo.findByDonViChaIdAndDaXoa((list.get(i).getId()), false);
					if (listDmDonViCha != null && listDmDonViCha.size() > 0 && list.get(i).getId()>list.get(i).getDonViChaId()) {
						List<DmDonVi> listNull = new ArrayList<DmDonVi>();
						listKetQua.addAll(listCha(listDmDonViCha, listNull, strReturn(hienThi)));
					}
				}
			}
		}
		return listKetQua.stream().distinct().collect(Collectors.toList());
	}

	public List<DmDonVi> findByDmDonViChaIdAndDaXoa(Long DmDonViChaId, Boolean daXoa) {
		return dmDonViRepo.findByDonViChaIdAndDaXoa(DmDonViChaId, daXoa);
	}

	public String strReturn(String hienthi) {
		StringBuilder strbder = new StringBuilder();
		strbder.append(hienthi);
		strbder.append("");
		return strbder.toString();
	}

	public List<DmDonVi> findByDaXoa(Boolean daXoa) {
		return dmDonViRepo.findByDaXoa(daXoa);
	}

}
