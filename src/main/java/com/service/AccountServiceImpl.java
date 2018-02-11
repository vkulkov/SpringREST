package com.service;

import com.domain.Account;
import com.persistence.AccountDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service("accountService")
@Transactional(propagation = Propagation.REQUIRED)
public class AccountServiceImpl implements AccountService {

    private final AccountDAO accountDAO;

    @Inject
    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void createAccount(Account account) {
        accountDAO.createAccount(account);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Account getAccount(Long id) throws AccountNotFoundException {
        return accountDAO.getAccountById(id);
    }
}
