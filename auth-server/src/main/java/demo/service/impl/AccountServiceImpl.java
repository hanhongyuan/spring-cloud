package demo.service.impl;

import demo.domain.Account;
import demo.repository.AccountRepository;
import demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by João on 24/06/2016.
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account create(Account account) {
        account.encryptPassword();
        return this.accountRepository.save(account);
    }

    @Override
    public Account find(Long id) {
        return this.accountRepository.findOne(id);
    }
}
