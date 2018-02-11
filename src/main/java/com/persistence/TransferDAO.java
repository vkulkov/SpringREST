package com.persistence;

import com.domain.Transfer;

public interface TransferDAO {
    void createTransfer(Transfer transfer);
    Transfer getTransferById(Long id);
}
