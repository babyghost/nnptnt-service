package vn.dnict.microservice.uaa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TokenCreationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1008552894910101608L;

	public TokenCreationException() {
	}

	public TokenCreationException(String message) {
		super(message);
	}

	public TokenCreationException(String message, Throwable cause) {
		super(message, cause);
	}
}
