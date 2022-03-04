package vn.dnict.microservice.uaa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameTakenException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2052604782693575773L;

	public UsernameTakenException() {
	}

	public UsernameTakenException(String message) {
		super(message);
	}
}
