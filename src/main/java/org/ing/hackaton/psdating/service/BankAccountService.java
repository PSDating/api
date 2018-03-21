package org.ing.hackaton.psdating.service;

import org.ing.hackaton.psdating.MockData;
import org.ing.hackaton.psdating.domain.BankAccount;
import org.ing.hackaton.psdating.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public final class BankAccountService {

    public final List<BankAccount> getAccounts(final User user) {
        return MockData.BANK_ACCOUNTS;
    }
}
