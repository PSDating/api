package org.ing.hackaton.psdating;

import org.ing.hackaton.psdating.domain.Bank;
import org.ing.hackaton.psdating.domain.BankAccount;
import org.ing.hackaton.psdating.domain.Recommendation;
import org.ing.hackaton.psdating.domain.Transaction;
import org.ing.hackaton.psdating.domain.User;

import java.util.Arrays;
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

    public static final List<String> ALTERNATIVES = Arrays.asList(
            "Praxis Rotterdam",
            "Albert Heijn Rotterdam"
    );


    public static final Recommendation getMockData(final String companyName) {
        switch(companyName) {
        case "Praxis Rotterdam":
            return Recommendation.builder().companyName(companyName).logoUrl("http://defabriek.nl/_wpx/wp-content/uploads/2013/08/Praxis-logo.jpg").address("yep").zipCity("Rotterdam").phonenumber("010-1234567").build();
        case "Albert Heijn Rotterdam":
            return Recommendation.builder().companyName(companyName).logoUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Albert_Heijn_Logo.svg/2000px-Albert_Heijn_Logo.svg.png").address("yep").zipCity("Rotterdam")
                .phonenumber("010-2").build();
        }
        return null;
    }
}
