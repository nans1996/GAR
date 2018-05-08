package com.gar.payingservice.service;

import com.gar.payingservice.model.Account;
import com.gar.payingservice.repository.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountDateAccessImpl implements AccountDataAccess{

    private AccountRepository repository;

    public AccountDateAccessImpl(AccountRepository repository) {
        this.repository  = repository;
    }

    @Override
    public Account create(Account entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Account update(Account entity) {
        return repository.save(entity);
    }

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public Account getById(Long id) {
        Optional<Account> entity = repository.findById(id);
        return entity.get();
    }
}
