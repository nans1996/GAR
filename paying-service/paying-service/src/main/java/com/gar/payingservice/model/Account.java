package com.gar.payingservice.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeCard;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expirationDate;
    private int codeSecurity;
    private float balance;
    private String holder;

    public Account() {
    }

    public Long getCodeCard() {
        return codeCard;
    }

    public void setCodeCard(Long codeCard) {
        this.codeCard = codeCard;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCodeSecurity() {
        return codeSecurity;
    }

    public void setCodeSecurity(int codeSecurity) {
        this.codeSecurity = codeSecurity;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return codeSecurity == account.codeSecurity &&
                Objects.equals(codeCard, account.codeCard) &&
                Objects.equals(expirationDate, account.expirationDate) &&
                Objects.equals(holder, account.holder);
    }

    @Override
    public int hashCode() {

        return Objects.hash(codeCard, expirationDate, codeSecurity, balance, holder);
    }
}
