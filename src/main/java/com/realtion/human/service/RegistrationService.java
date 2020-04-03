package com.realtion.human.service;

import com.realtion.human.config.Constants;
import com.realtion.human.entity.Users;
import com.realtion.human.model.Response;
import com.realtion.human.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Created by isuraksha3 on 1/22/2019.
 */

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
public class RegistrationService {

    @Autowired
    MessageSource messageSource;
    @Autowired
    Response response;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Allows user to register for any community using community code
     *
     * @param users -stores the user details provided by the user
     * @return - resisted user Id
     */
    @Transactional
    public Response register(Users users) {

        Response response = new Response();
        Users user = userRepository.findByEmailOrMobile(users.getEmail(), users.getMobile());
        if (user != null) {
            response.errorResponse("user is already registered");
            response.setData(user);
            return response;
        }
        String code = users.getName().trim().substring(0, 2);
        String returnedCode = getCode(code);
        users.setCode(returnedCode);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setStatus(Constants.ACTIVE);
        response.setMessage("user registered successfully");
        userRepository.save(users);
        return response;
    }

    private String getCode(String code) {
        String otpString = String.format("%03d", 100 + new Random().nextInt(900));
        return code + "0" + otpString;
    }

}
