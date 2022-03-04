package vn.dnict.microservice.uaa.dao.service.auth;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import lombok.extern.slf4j.Slf4j;
import vn.dnict.microservice.uaa.dao.model.UserLog;
import vn.dnict.microservice.uaa.dao.service.UserLogService;

@Slf4j
public class CasUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {
	@Autowired
	private UsernamePasswordDetailsService usernamePasswordDetailsService;
	@Autowired
	private UserLogService userLogService;

	public CasUserDetailsService() {
		super();
	}

	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
		String login = token.getPrincipal().toString();
		WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) token.getDetails();
		log.info("login '{}'" + token.getName());
		String email = login.toLowerCase().trim();
		UserDetails user = usernamePasswordDetailsService.loadUserByUsername(email);
		UserLog userLog = new UserLog();
		userLog.setEmail(email);
		userLog.setIp(webAuthenticationDetails.getRemoteAddress());
		userLog.setStatus(true);
		userLog.setNgayTao(LocalDateTime.now());
		userLog.setNgayCapNhat(LocalDateTime.now());
		userLogService.save(userLog);
		log.info("CasUserDetailsService '{line 41}'" + login);
		return user;
	}
}
