package demo.service.impl;

import demo.domain.Account;
import demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by João on 24/06/2016.
 */

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = this.accountRepository.findByUsername(username);
        if (account.isPresent()) {
            Account acc = account.get();
            acc.getAuthorities().size(); // force lazy collection
            return acc;
        } else {
            throw new UsernameNotFoundException("couldn't find the user " + username);
        }
    }
}
