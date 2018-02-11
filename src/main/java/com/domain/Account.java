package com.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "account")
public class Account {

    private Long id;

    private BigDecimal balance = new BigDecimal(0);

    public Account(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "money")
    public BigDecimal getBalance() {
        return balance;
    }

    public void addMoneyToBalance(BigDecimal moneyAmount) {
        this.balance = this.balance.add(moneyAmount);
    }

    public void subtractMoneyFromBalance(BigDecimal moneyAmount) throws NegativeMoneyAmountException {
        BigDecimal difference = this.balance.subtract(moneyAmount);
        if (difference.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeMoneyAmountException();
        }
        this.balance = difference;
    }

    @Override
    public String toString() {
        return "Account [" +
                "id=" + id +
                ", balance=" + balance +
                "]";
    }
}
