package org.ing.hackaton.psdating.controllers;

import org.ing.hackaton.psdating.MockData;
import org.ing.hackaton.psdating.domain.AccountResponse;
import org.ing.hackaton.psdating.domain.Bank;
import org.ing.hackaton.psdating.domain.User;
import org.ing.hackaton.psdating.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @CrossOrigin({"35.205.253.173:8080", "localhost:8080"})
    @RequestMapping
    public AccountResponse authorizeAccount(@RequestParam final String bankName, @RequestParam final String username,
            @RequestParam(required = false) final String password, @RequestParam(required = false) final String bankCardNumber) {
        final User user = MockData.USER;
        final Bank bank = Bank.valueOf(bankName.toUpperCase());
        return bankAccountService.loginWithBank(user, bank, username, password, bankCardNumber);
    }

}
