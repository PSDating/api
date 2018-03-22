package org.ing.hackaton.psdating.service;

import org.ing.hackaton.psdating.MockData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public final class KvkService {

    private KvkService() {}

    List<String> findAlternatives(final String partyName) {
        return MockData.findAlternatives(partyName);
    }

}
