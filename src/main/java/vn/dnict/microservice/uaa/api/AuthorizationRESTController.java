package vn.dnict.microservice.uaa.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.dnict.microservice.uaa.data.TokenUserDetails;

@CrossOrigin(maxAge = 3600)
// @CrossOrigin
@RestController
@RequestMapping("/uaa")
public class AuthorizationRESTController {
	
//	@CrossOrigin(origins = "http://localhost:9527")
	@GetMapping("/token")
	public String getToken(@AuthenticationPrincipal TokenUserDetails principal) {
		return principal.getToken();
		// return ResponseEntity.ok(principal);
	}

	@GetMapping("/cas/login")
	public String getCasToken(@AuthenticationPrincipal TokenUserDetails principal) {
		// log.info(""+String.valueOf(principal.));
		// return "cas";
		return principal.getToken();
		// return ResponseEntity.ok(principal);
	}
}
