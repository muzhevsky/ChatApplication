package org.application.controllers;

import org.application.dtos.AccountCreationForm;
import org.application.services.signup.UserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    @Autowired
    @Qualifier("userSignupService")
    UserSignUpService userSignUpService;


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/signup/user")
    public Boolean signUp(@RequestBody AccountCreationForm form){
        try{
            userSignUpService.signUp(form);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
