package org.application.services.signup;

import lombok.SneakyThrows;
import org.application.entities.AccountEntity;
import org.application.exceptions.UserAlreadyExistException;
import org.application.repos.AccountRepository;
import org.application.services.encryption.DefaultPasswordEncryptionService;
import org.application.services.encryption.SaltGenerationService16;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("createAccountService")
public class DefaultCreateAccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private DefaultPasswordEncryptionService encryptionService;
    @Autowired
    private SaltGenerationService16 saltGenerationService;

    @SneakyThrows
    public AccountEntity createAccount(AccountEntity accountEntity) {
        var accounts = accountRepository.findFirstByEmail(accountEntity.getEmail());
        if (accounts!=null)
            throw new UserAlreadyExistException("already exist");

        var salt = saltGenerationService.getSalt();
        var password = encryptionService.encryptPassword(accountEntity.getPassword(), salt);


        accountEntity.setPassword(password);
        accountEntity.setSalt(salt);

        return accountEntity;
    }
}
