package org.ing.hackaton.psdating.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class User {

    private final String username;
    private final String password;
}
