package vn.dnict.microservice.security.model;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		Optional<String> result = Optional.empty();
		if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof TokenUserDetails) {

			return Optional.of(((TokenUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getProfileName());
		} else {
			return result;
		}
	}
}
