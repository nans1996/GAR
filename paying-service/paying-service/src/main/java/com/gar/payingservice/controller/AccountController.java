package com.gar.payingservice.controller;

import com.gar.payingservice.model.Account;
import com.gar.payingservice.service.AccountDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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

    @RequestMapping(value = "/purchase", method = RequestMethod.PUT)
    public boolean purchase(@RequestParam("account") Account account) {
        boolean result = false;
        Account systemAccount = accountDataAccess.getById(account.getCodeCard());
        if (systemAccount.equals(account)) {
            systemAccount.setBalance(systemAccount.getBalance()-account.getBalance());
            accountDataAccess.update(systemAccount);
        }
        return result;
    }

    @RequestMapping(value = "/cardValidity", method = RequestMethod.PUT)
    public boolean cardValidity(@RequestParam("account") Account account) {
        boolean result = true;
        //заполненность полей
        //Алгоритм Луна
        //что-то еще ...соответствие
        return result;
    }

}
