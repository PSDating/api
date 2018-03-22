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
            Transaction.builder().partyName("Gamma Rotterdam Blijdorp").build(),
            Transaction.builder().partyName("Jumbo 's-Gravelandseweg").build()
    );

    public static List<String> findAlternatives(final String partyName) {
        if (partyName.equals("Gamma Rotterdam Blijdorp")) {
            return Arrays.asList(
                    "Praxis Rotterdam Zuid",
                    "Hornbach Nieuwerkerk a/d IJssel",
                    "Formido Rotterdam"
            );
        }
        if (partyName.equals("Jumbo 's-Gravelandseweg")) {
            return Arrays.asList(
                    "Albert Heijn Mathenesserplein"
            );
        }
        return Collections.emptyList();
    }

    public static final Recommendation getInfoOnCompany(final String companyName) {
        switch(companyName) {
        case "Praxis Rotterdam Zuid":
            return Recommendation.builder()
                    .companyName(companyName)
                    .logoUrl("https://d16m3dafbknje9.cloudfront.net/imagescaler/9089876590622-424-226.jpg")
                    .address("Stadionweg 31 31F")
                    .zipCity("3077 AP Rotterdam")
                    .city("Rotterdam")
                    .phonenumber("010 - 43 22 504")
                    .url("https://www.praxis.nl/")
                    .build();
        case "Hornbach Nieuwerkerk a/d IJssel":
            return Recommendation.builder()
                    .companyName(companyName)
                    .logoUrl("https://www.hornbach.nl/cms/media/nl/teaser_algemeen/vestigingsinfo_1/mijn_hornbach/Drive-in-478x444_Pic_362.jpg")
                    .address("Laan van Avant-Garde 1")
                    .zipCity("2913 LA Nieuwerkerk a/d IJssel")
                    .city("Nieuwerkerk a/d IJssel")
                    .phonenumber(null)
                    .url(null)
                    .build();
        case "Formido Rotterdam":
            return Recommendation.builder()
                    .companyName(companyName)
                    .logoUrl("https://cdn.formido.nl/default/kega_store/54550.jpg")
                    .address(null)
                    .zipCity(null)
                    .city(null)
                    .phonenumber(null)
                    .url(null)
                    .build();
        case "Albert Heijn Mathenesserplein":
            return Recommendation.builder()
                    .companyName(companyName)
                    .logoUrl("https://www.openingstijden.nl/assets/generated/business_logo/5688.png")
                    .address("Mathenesserplein 76")
                    .zipCity("3022 LD Rotterdam")
                    .city("Rotterdam")
                    .phonenumber(null)
                    .url(null)
                    .build();
        }
        return null;
    }

}
