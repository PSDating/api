package org.ing.hackaton.psdating.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class BankAccountController {

    @RequestMapping
    public String account() {
        return "account";
    }

}
