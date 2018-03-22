package org.ing.hackaton.psdating.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class AccountResponse {

    private final String status;
    private final String challengeUrl;
}
