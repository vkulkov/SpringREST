package com.service;

import com.domain.Transfer;

public interface TransferService {
    void createTransfer(Transfer transfer) throws InsufficientFundsException;
    Transfer getTransfer(Long id) throws TransferNotFoundException;
}
