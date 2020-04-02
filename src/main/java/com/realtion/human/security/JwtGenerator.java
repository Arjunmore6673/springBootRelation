package com.realtion.human.security;

import com.realtion.human.model.jwt.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {

	/**
	 * Generated the token
	 * 
	 * @param jwtUser-stores the details provided by user in jwt pojo class for
	 *                       token generation
	 * @return - the generated token as string
	 */

	public String generate(JwtUser jwtUser) {

		Claims claims = Jwts.claims().setSubject(String.valueOf(jwtUser.getId()));
		claims.put("username", jwtUser.getUsername());
		claims.put("id", String.valueOf(jwtUser.getId()));
		claims.put("role", jwtUser.getRole());
		claims.put("status", jwtUser.getStatus());

		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "youtube").compact();
	}
}
