package com.realtion.human.controller;

import com.realtion.human.config.GlobalConstant;
import com.realtion.human.model.LoginForm;
import com.realtion.human.repository.UserRepository;
import com.realtion.human.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping(GlobalConstant.LOGIN)
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    ResponseEntity<HashMap<String, String>> login(@Valid @RequestBody LoginForm loginForm) {

        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        String data = loginService.getToken(username, password);
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", data);
        return ResponseEntity.ok().body(tokenMap);
    }

}


//    @GetMapping(GlobalConstant.FORGET_PASSWORD)
//    public ResponseMessage forgetPassword(@RequestParam("username") String username) {
//        forgotPasswordService.forgetPassword(username);
//        return new ResponseMessage(messageSource.getMessage("otp.send", null, LocaleContextHolder.getLocale()));
//    }

//    @ApiResponses(value = {@ApiResponse(code = 401, message = "Unauthorised request"),
//            @ApiResponse(code = 200, message = "The request is complete, and  the password is successfully changed"),
//            @ApiResponse(code = 404, message = "User not found")})
//    @PostMapping(GlobalConstant.CHANGE_PASSWORD)
//    public ResponseMessage changePassword(@Valid @RequestBody ChangePasswordForm changePasswordForm) {
//        forgotPasswordService.changePassword(changePasswordForm);
//        return new ResponseMessage(messageSource.getMessage("password.change", null, LocaleContextHolder.getLocale()));
//    }
//
//    @PostMapping(GlobalConstant.CHECK_OTP)
//    public ResponseMessage checkOtp(@Valid @RequestBody OtpForm otpform) {
//        forgotPasswordService.checkOtp(otpform);
//        return new ResponseMessage(messageSource.getMessage("otp.valid", null, LocaleContextHolder.getLocale()));
//    }
