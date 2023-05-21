package org.application.services.signup;

import org.application.dtos.AccountCreationForm;
import org.application.entities.AccountEntity;
import org.application.entities.UserEntity;
import org.application.exceptions.UserAlreadyExistException;
import org.application.repos.AccountRepository;
import org.application.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("userSignupService")
public class UserSignUpService{

    @Autowired
    @Qualifier("createAccountService")
    private DefaultCreateAccountService createAccountService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("accountRepository")
    private AccountRepository accountRepository;

    public void signUp(AccountCreationForm form) {
        var accountEntity = new AccountEntity(form);

        accountEntity.setPassword(form.getPassword().getBytes());

        accountEntity = createAccountService.createAccount(accountEntity);
        accountEntity = accountRepository.save(accountEntity);

        var userModel = new UserEntity(accountEntity.getId());
        userRepository.save(userModel);
    }
}
