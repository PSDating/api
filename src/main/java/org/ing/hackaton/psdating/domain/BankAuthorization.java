package org.ing.hackaton.psdating.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
public class BankAuthorization {
    public String bankName;
    public String bankAccountNumber;
    public String bankCardNumber;
}
