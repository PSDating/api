package org.ing.hackaton.psdating.service;

import org.ing.hackaton.psdating.MockData;
import org.ing.hackaton.psdating.domain.BankAccount;
import org.ing.hackaton.psdating.domain.Recommendation;
import org.ing.hackaton.psdating.domain.Transaction;
import org.ing.hackaton.psdating.domain.User;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public final class RecommendationService {

    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private RecommendationService recommendationService;
    @Autowired
    private KvkService kvkService;
    @Autowired
    private GoogleSearchService googleSearchService;

    public List<Recommendation> fetchRecommendationsForUser(final User user) throws IOException, ParseException {
        final List<String> results = new ArrayList<>();

        final List<BankAccount> accounts = bankAccountService.getAccounts(user);
        for (final BankAccount account : accounts) {
            final List<Transaction> transactions = transactionService.fetchTransactions(account);
            for (final Transaction transaction : transactions) {
                final String partyName = transaction.getPartyName();
                results.addAll(kvkService.findAlternatives(partyName));
            }
        }

        final List<Recommendation> recommendations = new ArrayList<>();
        for (final String companyName : results) {
            final Recommendation recommendation = recommendationService.collectInfoOnRecommendation(companyName);
            if (recommendation != null) {
                recommendations.add(recommendation);
            }
        }

        return recommendations;

    }

    private Recommendation collectInfoOnRecommendation(final String companyName) throws IOException, ParseException {
        return googleSearchService.collectInfoOnCompany(companyName);
    }
}
