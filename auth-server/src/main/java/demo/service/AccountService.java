package demo.service;

import demo.domain.Account;

/**
 * Created by João on 24/06/2016.
 */

public interface AccountService {

    Account create(Account account);

    Account find(Long id);

}
