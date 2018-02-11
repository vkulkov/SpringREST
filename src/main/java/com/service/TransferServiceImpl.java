package com.service;

import com.domain.Account;
import com.domain.NegativeMoneyAmountException;
import com.domain.Transfer;
import com.persistence.AccountDAO;
import com.persistence.TransferDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;

@Service("transferService")
@Transactional(propagation = Propagation.REQUIRED)
public class TransferServiceImpl implements TransferService {

    private final TransferDAO transferDAO;
    private final AccountDAO accountDAO;

    @Inject
    public TransferServiceImpl(TransferDAO transferDAO, AccountDAO accountDAO) {
        this.transferDAO = transferDAO;
        this.accountDAO = accountDAO;
    }

    @Override
    public void createTransfer(Transfer transfer) throws InsufficientFundsException {
        Account creditor = transfer.getCreditor();
        Account debtor = transfer.getDebtor();
        BigDecimal amount = transfer.getAmount();
        try {
            creditor.subtractMoneyFromBalance(amount);
            debtor.addMoneyToBalance(amount);
            transferDAO.createTransfer(transfer);
            accountDAO.updateAccount(creditor);
            accountDAO.updateAccount(debtor);
        } catch (NegativeMoneyAmountException e) {
            throw new InsufficientFundsException();
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Transfer getTransfer(Long id) throws TransferNotFoundException {
        return transferDAO.getTransferById(id);
    }
}
