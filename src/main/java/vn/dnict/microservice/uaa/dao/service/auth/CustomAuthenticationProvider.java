package vn.dnict.microservice.uaa.dao.service.auth;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import vn.dnict.microservice.uaa.dao.model.UserLog;
import vn.dnict.microservice.uaa.dao.service.UserLogService;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private UsernamePasswordDetailsService userDetailsService;
	@Autowired
	private UserLogService userLogService;

	public CustomAuthenticationProvider() {
		super();
	}

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		log.info("authenticate CustomAuthenticationProvider {line 33}: ");
		final String name = authentication.getName().trim();
		WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) authentication.getDetails();
		if (Objects.nonNull(name) && !name.isEmpty()) {
			UserDetails principal = userDetailsService.loadUserByUsername(name.trim());
			final String password = String.valueOf(authentication.getCredentials());
			System.out.println("Check login: " + authentication.getCredentials());
			System.out.println("Check login: " + password);
			// lưu log login
			UserLog userLog = new UserLog();
			userLog.setEmail(name);
			userLog.setIp(webAuthenticationDetails.getRemoteAddress());
			userLog.setStatus(true);
			userLog.setNgayTao(LocalDateTime.now());
			userLog.setNgayCapNhat(LocalDateTime.now());
			userLogService.save(userLog);
//			if (principal.isEnabled() && password.equals("system")) {
			if (principal.isEnabled()) {
				final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password,
						principal.getAuthorities());
				log.info("CustomAuthenticationProvider {line 53}: " + auth);
				return auth;
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}