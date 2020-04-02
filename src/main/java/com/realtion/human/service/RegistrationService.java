package com.realtion.human.service;

import com.realtion.human.config.Constants;
import com.realtion.human.entity.Users;
import com.realtion.human.model.Response;
import com.realtion.human.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by isuraksha3 on 1/22/2019.
 */

@Service
public class RegistrationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageSource messageSource;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    Response response;

    /**
     * Allows user to register for any community using community code
     *
     * @param users         -stores the user details provided by the user
     * @return - resisted user Id
     */
    public Response register(Users users) {

        Response response = new Response();
        Users user = userRepository.findByEmailOrMobile(users.getEmail(), users.getMobile());
        if (user != null) {
            response.errorResponse("user is already registered");
            response.setData(user);
            return response;
        }
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setStatus(Constants.ACTIVE);
        response.setMessage("user registered successfully");
        response.setData(userRepository.save(users).getId());
        return response;
    }


}
