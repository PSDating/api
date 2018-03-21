package org.ing.hackaton.psdating.service;

import org.ing.hackaton.psdating.MockData;
import org.ing.hackaton.psdating.domain.BankAccount;
import org.ing.hackaton.psdating.domain.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public final class TransactionService {

    TransactionService() {}

    public final List<Transaction> fetchTransactions(final BankAccount account) {
        return MockData.TRANSACTIONS;
    }
}
