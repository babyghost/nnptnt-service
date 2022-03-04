package vn.dnict.microservice.uaa.data;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class TokenUserDetails extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6550023682037064151L;
	private String token;
	private String profileName;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	public TokenUserDetails(String username, String profileName, String password, String token, boolean enabled,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, true, true, true, authorities);
		logger.info("TokenUserDetails {TokenUserDetails: line 21}");
		this.profileName = profileName;
		this.token = token;
		logger.info("token {TokenUserDetails: line 24}: "+token);
	}

	public String getToken() {
		return token;
	}

	public String getProfileName() {
		return profileName;
	}
}
