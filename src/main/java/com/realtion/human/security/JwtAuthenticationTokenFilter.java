package com.realtion.human.security;

import com.realtion.human.model.jwt.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    MessageSource messageSource;

    public JwtAuthenticationTokenFilter() {
        super("/api/**");
    }

    /**
     * Checks whether the token provided is valid or not
     *
     * @param httpServletRequest-stores  the data of request
     * @param httpServletResponse-stores the data to be given as response
     * @return- authentication manager the token
     */

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if (httpServletRequest.getMethod().equalsIgnoreCase("OPTIONS")) {
            return null;
        }

        String header = httpServletRequest.getHeader("Authorization");
//		System.out.println("filter called....");
        if (header == null || !header.startsWith("Token ")) {
            throw new RuntimeException(
                    messageSource.getMessage("token.missing", null, LocaleContextHolder.getLocale()));
        }

        String authenticationToken = header.substring(6);

        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
