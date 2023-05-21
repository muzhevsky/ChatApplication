package org.application.services.signin;

import lombok.SneakyThrows;
import org.application.dtos.SignInForm;
import org.application.entities.AccountEntity;
import org.application.exceptions.UserNotFoundException;
import org.application.exceptions.WrongPasswordException;
import org.application.repos.AccountRepository;
import org.application.services.encryption.DefaultPasswordEncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DefaultSignInService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private DefaultPasswordEncryptionService encryptionService;

    public AccountEntity signIn(SignInForm signInForm) throws UserNotFoundException, WrongPasswordException{
        var accountEntity = accountRepository.findFirstByEmail(signInForm.getEmail());
        if (accountEntity == null)
            throw new UserNotFoundException("user not found");

        var inputPassword = signInForm.getPassword().getBytes();
        var passwordBytes = encryptionService.encryptPassword(
                inputPassword, accountEntity.getSalt());

        var dbPass = accountEntity.getPassword();
        if (!Arrays.equals(passwordBytes, dbPass)) {
            throw new WrongPasswordException();
        }

        return accountEntity;
    }
}