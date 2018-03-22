package org.ing.hackaton.psdating.controllers;

import org.ing.hackaton.psdating.MockData;
import org.ing.hackaton.psdating.domain.AccountResponse;
import org.ing.hackaton.psdating.domain.Bank;
import org.ing.hackaton.psdating.domain.BankAuthorization;
import org.ing.hackaton.psdating.domain.User;
import org.ing.hackaton.psdating.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@CrossOrigin
public final class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @RequestMapping
    public final AccountResponse authorizeAccount(@RequestBody BankAuthorization bankAuthorization) {
        final User user = MockData.USER;
        final Bank bank = Bank.valueOf(bankAuthorization.bankName.toUpperCase());
        return bankAccountService.loginWithBank(user, bank, bankAuthorization.bankAccountNumber, " ", bankAuthorization.bankCardNumber);
    }
}
