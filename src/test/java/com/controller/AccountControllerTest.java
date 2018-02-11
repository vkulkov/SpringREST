package com.controller;

import com.domain.Account;
import com.service.AccountService;
import com.service.AccountServiceImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    @Test
    public void shouldCreateAccount() {
        Account account = new Account();

        AccountService accountService = mock(AccountServiceImpl.class);

        AccountController controller = new AccountController(accountService);

        assertEquals(account, controller.createAccount(account));
    }

    @Test
    public void shouldGetAccountById() {
        Account expectedAccount = new Account();

        AccountService accountService = mock(AccountServiceImpl.class);

        when(accountService.getAccount(1L)).thenReturn(expectedAccount);

        AccountController controller = new AccountController(accountService);

        assertEquals(expectedAccount, controller.getAccount(1L));

        verify(accountService).getAccount(1L);
    }
}
