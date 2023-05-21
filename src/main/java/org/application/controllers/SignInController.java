package org.application.controllers;

import org.application.dtos.AccountCreationForm;
import org.application.dtos.SignInForm;
import org.application.entities.AccountEntity;
import org.application.exceptions.UserNotFoundException;
import org.application.exceptions.WrongPasswordException;
import org.application.services.signin.DefaultSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInController {

    @Autowired
    DefaultSignInService signInService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/signin")
    public ResponseEntity<AccountEntity> signIn(@RequestBody SignInForm body){
        try{
            var userId = signInService.signIn(body); // TODO: decide authorization method
            return new ResponseEntity<>(userId, HttpStatus.OK);
        }
        catch (UserNotFoundException | WrongPasswordException ex){
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}