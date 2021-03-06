package vn.dnict.microservice.security.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class TokenUserDetails extends User {
    /**
	 * 
	 */
	private static final long serialVersionUID = -341387683320142788L;
	private String token;
    private String profileName;

    public TokenUserDetails(String username, String profileName, String password, String token, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, true, true, true, authorities);
        this.profileName = profileName;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getProfileName() {
        return profileName;
    }

    public static TokenUserDetails fromUserObject(UserObject object) {
        return new TokenUserDetails(object.getUsername(), object.getProfileName(), "", object.getToken(), object.isEnabled(), object.getAuthorities().stream()
            .map(AuthorityObject::getAuthority)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList()));
    }
}
