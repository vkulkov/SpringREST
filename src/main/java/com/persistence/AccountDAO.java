package com.persistence;

import com.domain.Account;

public interface AccountDAO {
    void createAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Account account);
    Account getAccountById(Long id);
}
