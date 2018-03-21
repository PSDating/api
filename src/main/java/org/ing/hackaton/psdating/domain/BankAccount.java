package org.ing.hackaton.psdating.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
public final class BankAccount {

    private final Bank bank;
    private final String iban;
    private final String cardNr;

}
