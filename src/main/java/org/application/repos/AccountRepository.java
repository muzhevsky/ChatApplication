package org.application.repos;

import org.application.entities.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository("accountRepository")
public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {
    AccountEntity findFirstByEmail(String email);
}
