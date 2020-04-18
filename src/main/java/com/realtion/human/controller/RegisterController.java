package com.realtion.human.controller;

import com.realtion.human.config.GlobalConstant;
import com.realtion.human.entity.Users;
import com.realtion.human.model.LoginForm;
import com.realtion.human.model.Response;
import com.realtion.human.repository.UserRepository;
import com.realtion.human.service.LoginService;
import com.realtion.human.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;


@RestController
public class RegisterController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping(GlobalConstant.LOGIN)
    ResponseEntity<HashMap<String, Object>> login(@Valid @RequestBody LoginForm loginForm) {
        return loginService.getToken(loginForm.getUsername(), loginForm.getPassword());
    }


    @PostMapping(GlobalConstant.REGISTER)
    Response showRegister(@Valid @RequestBody Users users) {
        return registrationService.register(users);
    }

}
