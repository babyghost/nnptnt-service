//package vn.dnict.microservice.security.service;
//
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import lombok.extern.slf4j.Slf4j;
//import vn.dnict.microservice.security.model.TokenUserDetails;
//
//@Service
//@Slf4j
//public class UserService {
//    public String getUsername() {
//    	log.info("SecurityContextHolder.getContext():    "+SecurityContextHolder.getContext());
//        if (SecurityContextHolder.getContext() != null &&
//            SecurityContextHolder.getContext().getAuthentication() != null &&
//            SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof TokenUserDetails) {
//        	
//            return ((TokenUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getProfileName();
//        } else {
//            return "guest";
//        }
//    }
//}
