package vn.dnict.microservice.uaa.data;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private TokenProperties properties;

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		logger.info("getPreAuthenticatedPrincipal {TokenAuthenticationFilter: line 20}");
		if (request.getParameter(properties.getHeader()) != null) {
			return request.getParameter(properties.getHeader());
		}
		return request.getHeader(properties.getHeader());
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		logger.info("getPreAuthenticatedCredentials {TokenAuthenticationFilter: line 29}");
		if (request.getParameter(properties.getHeader()) != null) {
			return request.getParameter(properties.getHeader());
		}
		return request.getHeader(properties.getHeader());
	}

	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		logger.info("setAuthenticationManager {TokenAuthenticationFilter: line 39}");
		super.setAuthenticationManager(authenticationManager);
	}
}
