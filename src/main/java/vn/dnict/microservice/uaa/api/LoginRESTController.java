package vn.dnict.microservice.uaa.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.dnict.microservice.uaa.data.TokenUserDetails;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginRESTController {
	@GetMapping({ "", "/" })
	public String getToken(@AuthenticationPrincipal TokenUserDetails principal) {
		return principal.getToken();
		// return ResponseEntity.ok(principal);
	}
}
