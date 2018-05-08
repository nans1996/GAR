package com.gar.payingservice.service;

import com.gar.payingservice.model.Account;

import java.util.List;

public interface AccountDataAccess {
    Account create(Account function);
    void delete(Long id);
    Account update(Account function);
    Account getById(Long id);
    List<Account> getAll();
}

