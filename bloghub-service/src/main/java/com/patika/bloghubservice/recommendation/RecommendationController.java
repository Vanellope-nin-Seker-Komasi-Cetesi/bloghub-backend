package com.patika.bloghubservice.recommendation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

   /*@GetMapping
    public ResponseEntity<String> recommend() {
        String recommendations = recommendationService.recommend();
        return ResponseEntity.ok(recommendations);
    }
*/

    @GetMapping()
    public BlogRecommendation getRecommendationBlogs() {

        return recommendationService.getBlogRecommendations();
    }
}