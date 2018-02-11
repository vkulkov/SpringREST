package com.service;

import com.domain.Account;

import java.math.BigDecimal;

public interface AccountService {
    void createAccount(Account account);
    Account getAccount(Long id) throws AccountNotFoundException;
}
