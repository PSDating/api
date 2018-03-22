package org.ing.hackaton.psdating.service;

import org.ing.hackaton.psdating.MockData;
import org.ing.hackaton.psdating.domain.Recommendation;
import org.ing.hackaton.psdating.util.HttpUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Repository
public final class KvkService {

    private static final String BASE_URL = "https://api.kvk.nl/api/v2/testsearch/companies?city=";

    @Autowired
    private GoogleSearchService googleSearchService;

    private KvkService() {}

    List<String> findAlternatives(final String partyName) throws IOException, ParseException {

        final Recommendation recommendation = googleSearchService.collectInfoOnCompany(partyName);
        final String city = recommendation.getCity();

        final JSONObject jsonObject = HttpUtil.executeRequestForJsonObject(HttpMethod.GET, BASE_URL + city, Collections.emptyMap(), Collections.emptyMap());

//        System.out.println(recommendation);
//        System.out.println(jsonObject);

//        return Collections.emptyList();
        return MockData.findAlternatives(partyName);
    }

}
