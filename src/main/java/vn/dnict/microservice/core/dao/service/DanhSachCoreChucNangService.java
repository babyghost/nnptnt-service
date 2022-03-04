package vn.dnict.microservice.core.dao.service;

import java.util.List;

import vn.dnict.microservice.core.dao.model.DanhSachCoreChucNang;

public interface DanhSachCoreChucNangService {

	public List<DanhSachCoreChucNang> getRouters(List<String> roleNames);

}
