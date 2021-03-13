package com.realtion.human.security;

import com.realtion.human.model.jwt.JwtAuthenticationToken;
import com.realtion.human.model.jwt.JwtUser;
import com.realtion.human.model.jwt.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private JwtValidator validator;

	@Autowired
	MessageSource messageSource;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
												  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

	}

	/**
	 *Allows to grant authority to the currently logged user to access the Community App using username and token
	 * @param username- stores the username
	 * @param usernamePasswordAuthenticationToken-stores the token generated after login
	 * @return-AuthorityGranted
	 * @throws AuthenticationException
	 */

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
		String token = jwtAuthenticationToken.getToken();

		JwtUser jwtUser = validator.validate(token);

		if (jwtUser == null) {
			throw new UsernameNotFoundException("token.incorrect");
		}

		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(jwtUser.getRole());
		return new JwtUserDetails(jwtUser.getRole(), jwtUser.getId(),
				token,
				grantedAuthorities);
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
	}
}
