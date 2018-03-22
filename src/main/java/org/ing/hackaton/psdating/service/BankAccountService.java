package org.ing.hackaton.psdating.service;

import org.ing.hackaton.psdating.MockData;
import org.ing.hackaton.psdating.domain.AccountResponse;
import org.ing.hackaton.psdating.domain.Bank;
import org.ing.hackaton.psdating.domain.BankAccount;
import org.ing.hackaton.psdating.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public final class BankAccountService {

    public final List<BankAccount> getAccounts(final User user) {
        return MockData.BANK_ACCOUNTS;
    }

    public final AccountResponse loginWithBank(final User user, final Bank bank, final String username,
            final String password, final String bankCardNumber) {
        switch (bank) {
        case RABO:
            final String challengeUrl = obtainRaboChallenge(username, bankCardNumber);
            return AccountResponse.builder().status("CHALLENGE").challengeUrl(challengeUrl).build();
        case ABN:
        case ING:
            return AccountResponse.builder().status("OK").build();
        case SNS:
        default:
            return AccountResponse.builder().status("NYI").build();
        }
    }

    private String obtainRaboChallenge(String username, String bankCardNumber) {
        return "/OKB4tfPxc1W7yNxakSDdWINfbMGXWAjEXlIb3FJf1F7M_ly_a29ysrHo9Xme0mju6.png";
    }
}
