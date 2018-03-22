package org.ing.hackaton.psdating.controllers;

import org.ing.hackaton.psdating.MockData;
import org.ing.hackaton.psdating.domain.Recommendation;
import org.ing.hackaton.psdating.domain.User;
import org.ing.hackaton.psdating.service.RecommendationService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public final class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @RequestMapping("/recommendations")
    public final List<Recommendation> recommendations() throws IOException, ParseException {
        final User user = MockData.USER;
        return recommendationService.fetchRecommendationsForUser(user);
    }

}
