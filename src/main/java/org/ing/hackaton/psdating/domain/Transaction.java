package org.ing.hackaton.psdating.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class Transaction {
    private final String partyName;
}
