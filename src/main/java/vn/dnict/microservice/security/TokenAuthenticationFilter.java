//package vn.dnict.microservice.security;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
//import org.springframework.stereotype.Component;
//
//import vn.dnict.microservice.security.model.TokenProperties;
//
//@Component
//public class TokenAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//    @Autowired
//    private TokenProperties properties;
//    @Override
//    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
//        logger.info("Retrieving principal from token");
//        if(request.getParameter(properties.getHeader()) != null) {
//        	return request.getParameter(properties.getHeader());
//        }
//        return request.getHeader(properties.getHeader());
//    }
//
//    @Override
//    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
//        if(request.getParameter(properties.getHeader()) != null) {
//        	return request.getParameter(properties.getHeader());
//        }
//        return request.getHeader(properties.getHeader());
//    }
//
//    @Override
//    @Autowired
//    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
//        super.setAuthenticationManager(authenticationManager);
//    }
//}
//
