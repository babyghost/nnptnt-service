package vn.dnict.microservice.scheduled;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;
import vn.dnict.microservice.exceptions.EntityNotFoundException;

@Service
@Slf4j
public class DongBoScheduledTasks {
//	@Autowired
//	protected DongBoDuLieuBusiness dongBoDuLieuBusiness;
//
//	@Scheduled(fixedRate = 3600000) // 360000000 //100000
//	public void dongBoHoSo() throws JsonParseException, JsonMappingException, NoSuchFieldException, SecurityException,
//			IOException, EntityNotFoundException {
//		log.info("Chạy đồng bộ hồ sơ");
//		// dongBoDuLieuBusiness.dongBoMotCua();
//		return;
//	}

}
