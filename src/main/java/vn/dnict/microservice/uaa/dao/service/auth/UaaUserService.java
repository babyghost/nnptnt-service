package vn.dnict.microservice.uaa.dao.service.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import vn.dnict.microservice.uaa.data.TokenUserDetails;


@Service
public class UaaUserService {
    public String getUsername() {
    	//log.info("SecurityContextHolder.getContext():    "+SecurityContextHolder.getContext());
    	TokenUserDetails tokenUserDetails = this.getTokenUserDetails();
        if (tokenUserDetails != null ) {
            return tokenUserDetails.getProfileName();
        } else {
            return "guest";
        }
    }
    public TokenUserDetails getTokenUserDetails() {
    	//log.info("SecurityContextHolder.getContext():    "+SecurityContextHolder.getContext());
        if (SecurityContextHolder.getContext() != null &&
            SecurityContextHolder.getContext().getAuthentication() != null &&
            SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof TokenUserDetails) {        	
            return (TokenUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            return null;
        }
    }
}
