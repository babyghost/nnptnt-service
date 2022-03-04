package vn.dnict.microservice;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DongBo {
//	static final String URL_EMPLOYEES = "https://qa.danang.gov.vn/Dnict-ws-app-portlet/rest/csdl/getAllHoSoSyncToCSDL/";

//	@Test
//	public void contextLoads() {
//		// HttpHeaders
//		HttpHeaders headers = new HttpHeaders();
//
//		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
//		// Yêu cầu trả về định dạng JSON
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.set("tokenCode", "dnict$999");
//
//		// HttpEntity<String>: To get result as String.
//		HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//		// RestTemplate
//		RestTemplate restTemplate = new RestTemplate();
//
//		// Gửi yêu cầu với phương thức GET, và các thông tin Headers.
//		ResponseEntity<String> response = restTemplate.exchange(URL_EMPLOYEES, //
//				HttpMethod.GET, entity, String.class);
//
//		String result = response.getBody();
//
//		System.out.println(result);
//	}
}
