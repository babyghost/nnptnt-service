package vn.dnict.microservice.uaa.dao.service.auth;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import vn.dnict.microservice.uaa.dao.model.Role;
import vn.dnict.microservice.uaa.dao.model.User;
import vn.dnict.microservice.uaa.dao.model.UserLog;
import vn.dnict.microservice.uaa.dao.service.UserLogService;
import vn.dnict.microservice.uaa.dao.service.UserService;
import vn.dnict.microservice.uaa.data.TokenUserDetails;
import vn.dnict.microservice.uaa.exception.UserNotFoundException;

@Service
@Slf4j
public class UsernamePasswordDetailsService implements UserDetailsService {
	private UserService userService;
	private TokenService tokenService;
	@Autowired
	private UserLogService userLogService;

	@Autowired
	public UsernamePasswordDetailsService(UserService userService, TokenService tokenService) {
		this.userService = userService;
		this.tokenService = tokenService;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.info("-- UsernamePasswordDetailsService {line 39} " + email);
		try {
			return getUserDetails(userService.findByEmail(email), email);
		} catch (UserNotFoundException ex) {
			// lưu log login
			UserLog userLog = new UserLog();
			userLog.setEmail(email);
			userLog.setStatus(false);
			userLog.setNgayTao(LocalDateTime.now());
			userLog.setNgayCapNhat(LocalDateTime.now());
			userLogService.save(userLog);
			throw new UsernameNotFoundException("Account for '" + email + "' not found", ex);
		}
	}

	private TokenUserDetails getUserDetails(User user, String email) {
		try {
			log.info("User truy cập: " + user.getEmail());
			log.info("TokenUserDetails {line 57}: return 1 TokenUserDetails");
			return new TokenUserDetails(user.getEmail(), user.getUsername(), user.getPassword(), tokenService.encode(user), user.isEnabled(), getAuthorities(user.getRoles()));
		} catch (Exception e) {
			// lưu log login
			UserLog userLog = new UserLog();
			userLog.setEmail(email);
			userLog.setStatus(false);
			userLog.setNgayTao(LocalDateTime.now());
			userLog.setNgayCapNhat(LocalDateTime.now());
			userLogService.save(userLog);
			throw new UserNotFoundException("Tài khoản không tồn tại");
		}
	}

	private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}
//	private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
//		List<String> roleAndPermissions = new ArrayList<String>();
//		String[] roleNames = new String[roles.size()];
//		for (Role role : roles) {
//			roleAndPermissions.add(role.getRole());
//		}
//		Collection<GrantedAuthority> authorities = AuthorityUtils
//				.createAuthorityList(roleAndPermissions.toArray(roleNames));		
//		return authorities;
//	}
}
