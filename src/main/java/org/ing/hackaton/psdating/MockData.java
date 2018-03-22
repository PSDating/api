package org.ing.hackaton.psdating;

import org.ing.hackaton.psdating.domain.Bank;
import org.ing.hackaton.psdating.domain.BankAccount;
import org.ing.hackaton.psdating.domain.Recommendation;
import org.ing.hackaton.psdating.domain.Transaction;
import org.ing.hackaton.psdating.domain.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class MockData {

    private MockData() {}

    public static final User USER = User.builder().username("Raoul").password("DaBomb").build();

    public static final List<BankAccount> BANK_ACCOUNTS = Arrays.asList(
            BankAccount.builder().bank(Bank.RABO).iban("NL54RABO0115770232").build()
    );

    public static final List<Transaction> TRANSACTIONS = Arrays.asList(
            Transaction.builder().partyName("Gamma Rotterdam").build(),
            Transaction.builder().partyName("Jumbo Rotterdam").build()
    );

    public static List<String> findAlternatives(final String partyName) {
        if (partyName.equals("Gamma Rotterdam")) {
            return Arrays.asList(
                    "Praxis Rotterdam Zuid"
            );
        }
        if (partyName.equals("Jumbo Rotterdam")) {
            return Arrays.asList(
                    "Albert Heijn Mathenesserplein"
            );
        }
        return Collections.emptyList();
    }
}
