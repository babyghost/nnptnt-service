package vn.dnict.microservice.uaa.dao.service.auth;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.extern.slf4j.Slf4j;
import vn.dnict.microservice.uaa.dao.model.Role;
import vn.dnict.microservice.uaa.dao.model.User;
import vn.dnict.microservice.uaa.data.TokenProperties;
import vn.dnict.microservice.uaa.exception.TokenCreationException;

@Service
@Slf4j
public class TokenService {
	private TokenProperties properties;
	private String issuer;
	private Algorithm algorithm;
	private JWTVerifier verifier;

	@Autowired
	public TokenService(TokenProperties properties, @Value("${spring.application.name}") String issuer)
			throws UnsupportedEncodingException {
		this.properties = properties;
		this.issuer = issuer;
		this.algorithm = Algorithm.HMAC256(properties.getSecret());
		this.verifier = JWT.require(algorithm).acceptExpiresAt(0).build();
	}

	public DecodedJWT decode(String token) {
		return this.verifier.verify(token);
	}

	public String encode(User user) {
		LocalDateTime now = LocalDateTime.now();
		try {
			return JWT.create().withIssuer(issuer).withSubject(user.getEmail())
					.withIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
					.withExpiresAt(Date.from(
							now.plusSeconds(properties.getMaxAgeSeconds()).atZone(ZoneId.systemDefault()).toInstant()))
					.withArrayClaim("role", user.getRoles().stream().map(Role::getRole).toArray(String[]::new))
					.withClaim("usr", user.getUsername())
					// .withClaim("appCode", user.getUsername())
					// .withAudience(audience)
					.sign(algorithm);
		} catch (JWTCreationException ex) {
			log.error("Cannot properly create token", ex);
			throw new TokenCreationException("Cannot properly create token", ex);
		}
	}
}
