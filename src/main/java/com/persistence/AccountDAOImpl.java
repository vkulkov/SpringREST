package com.persistence;

import com.domain.Account;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("accountDAO")
@Transactional
public class AccountDAOImpl implements AccountDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void createAccount(Account account) {
        em.persist(account);
    }

    @Override
    public void updateAccount(Account account) {
        em.merge(account);
    }

    @Override
    public void deleteAccount(Account account) {
        em.remove(account);
    }

    @Override
    public Account getAccountById(Long id) {
        return em.find(Account.class, id);
    }
}
