package com.gar.payingservice.controller;

import com.gar.payingservice.model.Account;
import com.gar.payingservice.service.AccountDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    public AccountController() {
    }

    @Autowired
    private AccountDataAccess accountDataAccess;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Account> getAll() {
        return accountDataAccess.getAll();
    }

}
