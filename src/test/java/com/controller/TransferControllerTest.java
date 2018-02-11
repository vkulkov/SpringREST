package com.controller;

import com.domain.Account;
import com.domain.Transfer;
import com.persistence.AccountDAO;
import com.persistence.AccountDAOImpl;
import com.persistence.TransferDAO;
import com.persistence.TransferDAOImpl;
import com.service.TransferService;
import com.service.TransferServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransferControllerTest {

    @Test
    public void shouldTransferMoneyFromCreditorToDebtor() {
        Account creditor = new Account();
        creditor.addMoneyToBalance(new BigDecimal(1000));

        Account debtor = new Account();
        debtor.addMoneyToBalance(new BigDecimal(100));

        Transfer transfer = new Transfer();
        transfer.setCreditor(creditor);
        transfer.setDebtor(debtor);
        transfer.setAmount(new BigDecimal(100));

        TransferDAO transferDAO = mock(TransferDAOImpl.class);
        AccountDAO accountDAO = mock(AccountDAOImpl.class);

        TransferService transferService = new TransferServiceImpl(transferDAO, accountDAO);

        TransferController controller = new TransferController(transferService);

        assertEquals(transfer, controller.createTransfer(transfer));

        assertEquals(new BigDecimal(900), creditor.getBalance());
        assertEquals(new BigDecimal(200), debtor.getBalance());
    }

    @Test
    public void shouldGetTransferById() {
        Transfer expectedTransfer = new Transfer();

        TransferService transferService = mock(TransferServiceImpl.class);

        when(transferService.getTransfer(1L)).thenReturn(expectedTransfer);

        TransferController controller = new TransferController(transferService);

        assertEquals(expectedTransfer, controller.getTransfer(1L));

        verify(transferService).getTransfer(1L);
    }
}
