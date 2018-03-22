package org.ing.hackaton.psdating.controllers;

import org.ing.hackaton.psdating.MockData;
import org.ing.hackaton.psdating.domain.Recommendation;
import org.ing.hackaton.psdating.domain.User;
import org.ing.hackaton.psdating.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @RequestMapping("/recommendations")
    public List<Recommendation> recommendations() {
        final User user = MockData.USER;
        return recommendationService.fetchRecommendationsForUser(user);
    }

}
