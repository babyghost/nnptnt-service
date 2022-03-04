package vn.dnict.microservice.nnptnt.qlvatnuoi.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import vn.dnict.microservice.exceptions.EntityNotFoundException;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.CoSoChanNuoi;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.DmLoaiVatNuoi;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.model.NamChanNuoi;
import vn.dnict.microservice.nnptnt.qlvatnuoi.dao.service.NamChanNuoiService;
import vn.dnict.microservice.nnptnt.qlvatnuoi.data.NamChanNuoiInput;


@Service
public class NamChanNuoiBusiness {
	
	@Autowired
	NamChanNuoiService serviceNamChanNuoiService;
	
	public Page<NamChanNuoi> findAll(int page, int size, String sortBy, String sortDir, String search,Integer quy) {
		Direction direction;
		if (sortDir.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
		Page<NamChanNuoi> pageNamChanNuoi = serviceNamChanNuoiService.findAll(search, quy,
				PageRequest.of(page, size, direction, sortBy));
		return pageNamChanNuoi;
	}
	public NamChanNuoi findById(Long id) throws EntityNotFoundException {
		Optional<NamChanNuoi> optional = serviceNamChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(NamChanNuoi.class, "id", String.valueOf(id));
		}
		return optional.get();
	}



	public NamChanNuoi create(NamChanNuoiInput NamChanNuoiInput) {
		NamChanNuoi namChanNuoi = new NamChanNuoi();
//		namChanNuoi.setDaXoa(false);
		namChanNuoi.setNam(NamChanNuoiInput.getNam());
		namChanNuoi.setQuy(NamChanNuoiInput.getQuy());
		namChanNuoi = serviceNamChanNuoiService.save(namChanNuoi);
		return namChanNuoi;
	}


	public NamChanNuoi update(Long id, NamChanNuoiInput NamChanNuoiInput) throws EntityNotFoundException {
		Optional<NamChanNuoi> optional = serviceNamChanNuoiService.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException(CoSoChanNuoi.class, "id", String.valueOf(id));
		}
		NamChanNuoi namChanNuoi = optional.get();
		namChanNuoi.setNam(NamChanNuoiInput.getNam());
		namChanNuoi.setQuy(NamChanNuoiInput.getQuy());
		namChanNuoi = serviceNamChanNuoiService.save(namChanNuoi);
		return namChanNuoi;
	}

		@DeleteMapping(value = { "/{id}" })
		public NamChanNuoi delete(Long id) throws EntityNotFoundException {
			Optional<NamChanNuoi> optional = serviceNamChanNuoiService.findById(id);
			if (!optional.isPresent()) {
				throw new EntityNotFoundException(DmLoaiVatNuoi.class, "id", String.valueOf(id));
			}
			NamChanNuoi namChanNuoi = optional.get();
//			namChanNuoi.setDaXoa(true);
			namChanNuoi = serviceNamChanNuoiService.save(namChanNuoi);
			return namChanNuoi;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Autowired
//	NamChanNuoiService serviceNamChanNuoiService;
//	
////	@Autowired
////	CoSoChanNuoiService serviceCoSoChanNuoiService;
//	
//	
//	
//	public Page<NamChanNuoi> findAll(int page, int size, String sortBy, String sortDir, String search,Integer quy) {
//		Direction direction;
//		if (sortDir.equals("ASC")) {
//			direction = Direction.ASC;
//		} else {
//			direction = Direction.DESC;
//		}
//		Page<NamChanNuoi> pageNamChanNuoi = serviceNamChanNuoiService.findAll(search, quy ,
//				PageRequest.of(page, size, direction, sortBy));
//		return pageNamChanNuoi;
//	}
////	public NamChanNuoi findById(Long id) throws EntityNotFoundException {
////		Optional<NamChanNuoi> optional = NamChanNuoiService.findById(id);
////		if (!optional.isPresent()) {
////			throw new EntityNotFoundException(NamChanNuoi.class, "id", String.valueOf(id));
////		}
////		return optional.get();
////	}
//	
////	public NamChanNuoi findBynam(String nam) throws EntityNotFoundException {
////		Optional<NamChanNuoi> optional = serviceNamChanNuoiService.findBynam(nam);
////		if (!optional.isPresent()) {
////			throw new EntityNotFoundException(NamChanNuoi.class, "nam", String.valueOf(nam));
////		}
////		return optional.get();
////	}
//
//	public NamChanNuoi create(NamChanNuoiInput NamChanNuoiInput) {
//		NamChanNuoi NamChanNuoi = new NamChanNuoi();
//		NamChanNuoi.setNam(NamChanNuoiInput.getNam());
//		NamChanNuoi.setQuy(NamChanNuoiInput.getQuy());
////		NamChanNuoi.setCoSoChanNuoiId(NamChanNuoiInput.getCoSoChanNuoiId());
//		return NamChanNuoi;
//	}
//
//	public NamChanNuoi update(Long id, NamChanNuoiInput NamChanNuoiInput) throws EntityNotFoundException {
//		Optional<NamChanNuoi> optional = NamChanNuoiService.findById(id);
//		if (!optional.isPresent()) {
//			throw new EntityNotFoundException(NamChanNuoi.class, "id", String.valueOf(id));
//		}
//		NamChanNuoi NamChanNuoi = new NamChanNuoi();
//		NamChanNuoi.setNam(NamChanNuoiInput.getNam());
//		NamChanNuoi.setQuy(NamChanNuoiInput.getQuy());
////		NamChanNuoi.setCoSoChanNuoiId(NamChanNuoiInput.getCoSoChanNuoiId());
//		return NamChanNuoi;
//	}
//
//	@DeleteMapping(value = { "/{id}" })
//	public NamChanNuoi delete(Long id) throws EntityNotFoundException {
//		Optional<NamChanNuoi> optional = NamChanNuoiService.findById(id);
//		if (!optional.isPresent()) {
//			throw new EntityNotFoundException(NamChanNuoi.class, "id", String.valueOf(id));
//		}
//		NamChanNuoi NamChanNuoi = optional.get();
//		NamChanNuoi = serviceNamChanNuoiService.save(NamChanNuoi);
//		return NamChanNuoi;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public Page<NamChanNuoi> findAll(int page, int size, String sortBy, String sortDir, String search,Integer quy) {
//		Direction direction;
//		if (sortDir.equals("ASC")) {
//			direction = Direction.ASC;
//		} else {
//			direction = Direction.DESC;
//		}
//		Page<NamChanNuoi> pageNamChanNuoi = serviceNamChanNuoiService.findAll(search, quy,
//				PageRequest.of(page, size, direction, sortBy));
//		return pageNamChanNuoi;
//	}
//
//
////	public NamChanNuoi findById(Long id) throws EntityNotFoundException {
////		Optional<NamChanNuoi> optional = NamChanNuoiService.findById(id);
////		if (!optional.isPresent()) {
////			throw new EntityNotFoundException(NamChanNuoi.class, "id", String.valueOf(id));
////		}
////		return optional.get();
////	}
//
//
//
//	public NamChanNuoi create(NamChanNuoiInput NamChanNuoiInput) {
//		NamChanNuoi namChanNuoi = new NamChanNuoi();
//		namChanNuoi.setNam(NamChanNuoiInput.getNam());
//		namChanNuoi.setQuy(NamChanNuoiInput.getQuy());
//		namChanNuoi = serviceNamChanNuoiService.save(namChanNuoi);
//		return namChanNuoi;
//	}
//	
//	public NamChanNuoi findBynam(String nam) throws EntityNotFoundException {
//		Optional<NamChanNuoi> optional = serviceNamChanNuoiService.findBynam(nam);
//		if (!optional.isPresent()) {
//			throw new EntityNotFoundException(NamChanNuoi.class, "nam", String.valueOf(nam));
//		}
//		return optional.get();
//	}
//
//	public NamChanNuoi update(Long id, NamChanNuoiInput NamChanNuoiInput) throws EntityNotFoundException {
//		Optional<NamChanNuoi> optional = NamChanNuoiService.findById(id);
//		if (!optional.isPresent()) {
//			throw new EntityNotFoundException(NamChanNuoi.class, "id", String.valueOf(id));
//		}
//		NamChanNuoi namChanNuoi = optional.get();
//		namChanNuoi.setNam(NamChanNuoiInput.getNam());
//		namChanNuoi.setQuy(NamChanNuoiInput.getQuy());
//		namChanNuoi = serviceNamChanNuoiService.save(namChanNuoi);
//		return namChanNuoi;
//	}
//
//	@DeleteMapping(value = { "/{id}" })
//	public NamChanNuoi delete(Long id) throws EntityNotFoundException {
//		Optional<NamChanNuoi> optional = NamChanNuoiService.findById(id);
//		if (!optional.isPresent()) {
//			throw new EntityNotFoundException(NamChanNuoi.class, "id", String.valueOf(id));
//		}
//		NamChanNuoi namChanNuoi = optional.get();
//		namChanNuoi = serviceNamChanNuoiService.save(namChanNuoi);
//		return namChanNuoi;
//	
//	}
//





