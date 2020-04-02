package com.realtion.human.service;

import com.realtion.human.config.Constants;
import com.realtion.human.model.Users;
import com.realtion.human.model.jwt.JwtUser;
import com.realtion.human.repository.UserRepository;
import com.realtion.human.security.JwtGenerator;
import exception.UnauthorisedRequestException;
import exception.UserAuthenticationException;
import exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    MessageSource messageSource;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    JwtGenerator jwtGenerator;
    @Autowired
    private UserRepository userRepository;

    public String getToken(String username, String password) {
        Users user = userRepository.findByEmailOrMobile(username, username);
        if (user != null) {

            if (Constants.DEACTIVE.equals(user.getStatus())) {
                System.out.println("BLOCKED");
                throw new UserAuthenticationException("user is blocker", "EX_CU_1200", "E", HttpStatus.OK);
            } else if (user.getStatus() == null) {
                throw new UserAuthenticationException("user status is empty", "EX_CU_1201", "E", HttpStatus.OK);
            }
            if (passwordEncoder.matches(password, user.getPassword())) {
                JwtUser jwtUser = new JwtUser();
                jwtUser.setId(user.getId());
                jwtUser.setUsername(username);
                jwtUser.setStatus(user.getStatus());
                return jwtGenerator.generate(jwtUser);
            } else {
                throw new UnauthorisedRequestException(
                        messageSource.getMessage("invalid.password", null, LocaleContextHolder.getLocale()));
            }
        } else {
            throw new UserNotFoundException(
                    messageSource.getMessage("unknown.user", null, LocaleContextHolder.getLocale()) + username);
        }

    }

}
