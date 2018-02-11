package com.persistence;

import com.domain.Transfer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("transferDAO")
@Transactional
public class TransferDAOImpl implements TransferDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createTransfer(Transfer transfer) {
        em.persist(transfer);
    }

    @Override
    public Transfer getTransferById(Long id) {
        return em.find(Transfer.class, id);
    }
}
