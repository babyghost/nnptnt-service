//package vn.dnict.microservice.security.service;
//
//import java.io.UnsupportedEncodingException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//
//import vn.dnict.microservice.security.model.TokenProperties;
//
//
//
//@Service
//public class TokenService {
//	private Algorithm algorithm;
//    private JWTVerifier verifier;
//    @Autowired
//    public TokenService(TokenProperties properties) throws UnsupportedEncodingException {
//        this.algorithm = Algorithm.HMAC256(properties.getSecret());
//        this.verifier = JWT.require(algorithm).acceptExpiresAt(0).build();
//    }
//
//    public DecodedJWT decode(String token) {
//        return this.verifier.verify(token);
//    }
//}
