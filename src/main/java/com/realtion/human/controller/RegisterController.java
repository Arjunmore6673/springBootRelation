package com.realtion.human.controller;

import com.realtion.human.config.GlobalConstant;
import com.realtion.human.entity.Users;
import com.realtion.human.model.Response;
import com.realtion.human.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(GlobalConstant.REGISTER)
public class RegisterController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping()
    Response showRegister(@Valid @RequestBody Users users) {
        return registrationService.register(users);
    }


}
