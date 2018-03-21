package org.ing.hackaton.psdating.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class Recommendation {

    private final String companyName;
    private final String logoUrl;
    private final String address;
    private final String zipCity;
    private final String phonenumber;
}
