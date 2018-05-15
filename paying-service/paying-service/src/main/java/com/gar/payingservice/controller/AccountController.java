package com.gar.payingservice.controller;

import com.gar.payingservice.model.Account;
import com.gar.payingservice.service.AccountDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    public AccountController() {
    }

    @Autowired
    private AccountDataAccess accountDataAccess;

//    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    public List<Account> getAll() {
//        return accountDataAccess.getAll();
//    }
//
//    @RequestMapping(value = "/purchase", method = RequestMethod.PUT)
//    public boolean purchase(@RequestParam("account") Account account, @RequestParam("purchaseValue") float purchaseValue) {
//        boolean result = false;
//        Account systemAccount = accountDataAccess.getById(account.getCodeCard());
//        if (systemAccount.equals(account) && systemAccount.getBalance() > purchaseValue) {
//            systemAccount.setBalance(systemAccount.getBalance() - purchaseValue);
//            accountDataAccess.update(systemAccount);
//            result = true;
//        }
//        return result;
//    }

    //Пока не знаю как обьект взять*
    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public boolean purchaseTest(@RequestParam("expirationDate") String expirationDate,@RequestParam("holder") String holder,@RequestParam("codeSecurity") int codeSecurity,@RequestParam("codeCard") Long codeCard, @RequestParam("purchaseValue") float purchaseValue) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Date docDate= format.parse(expirationDate);
        Account account = new Account(docDate,codeSecurity,holder,codeCard);
        boolean result = false;
        Account systemAccount = accountDataAccess.getById(account.getCodeCard());
        if (systemAccount.equals(account) && systemAccount.getBalance() > purchaseValue) {
            systemAccount.setBalance(systemAccount.getBalance() - purchaseValue);
            accountDataAccess.update(systemAccount);
            result = true;
        }
        return result;
    }
}
