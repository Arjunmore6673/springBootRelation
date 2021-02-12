package com.realtion.human.service;

import com.realtion.human.config.Constants;
import com.realtion.human.entity.Users;
import com.realtion.human.model.Response;
import com.realtion.human.model.UsersModel;
import com.realtion.human.model.jwt.JwtUser;
import com.realtion.human.repository.UserRepository;
import com.realtion.human.security.JwtGenerator;
import exception.UnauthorisedRequestException;
import exception.UserAuthenticationException;
import exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
public class LoginService {

    @Autowired
    MessageSource messageSource;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    JwtGenerator jwtGenerator;
    @Autowired
    private UserRepository userRepository;

    public Response getToken(String username, String password) {
        Response response = new Response();
        HashMap<String, Object> tokenMap = new HashMap<>();
        ModelMapper mapper = new ModelMapper();
        Users user = userRepository.findByEmailOrMobile(username, username);
        if (user != null) {
            if (Constants.DEACTIVE.equals(user.getStatus())) {
                System.out.println("BLOCKED");
                throw new UserAuthenticationException("user is blocker", "EX_CU_1200", "E", HttpStatus.OK);
            } else if (user.getStatus() == null) {
                response.errorResponse("user status is empty");
                return response;
            }
            if (passwordEncoder.matches(password, user.getPassword())) {
                JwtUser jwtUser = new JwtUser();
                jwtUser.setId(user.getId());
                jwtUser.setUsername(username);
                jwtUser.setStatus(user.getStatus());
                String token = jwtGenerator.generate(jwtUser);
                tokenMap.put("token", token);
                tokenMap.put("user", mapper.map(user, UsersModel.class));
                response.successResponse(tokenMap);
            } else {
                response.errorResponse("Given password is Invalid..!");
            }
            return response;

        } else {
            response.errorResponse("User not found..!");
            return response;
        }

    }

}
