package com.realtion.human.security;

import com.realtion.human.model.jwt.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {


	private String secret = "youtube";

	public JwtUser validate(String token) {

		JwtUser jwtUser = null;
		try {
			Claims body = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();

			jwtUser = new JwtUser();
			jwtUser.setUsername((String) body.get("username"));
			jwtUser.setId(Long.parseLong((String) body.get("id")));
			jwtUser.setRole((String) body.get("role"));
		}
		catch (Exception e) {
			System.out.println(e);
		}

		return jwtUser;
	}
}

