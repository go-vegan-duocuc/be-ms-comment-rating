package cl.govegan.ms_comment_rating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.govegan.ms_comment_rating.models.Rating;
import cl.govegan.ms_comment_rating.services.RatingServices;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {

   @Autowired
   private RatingServices ratingServices;

   @GetMapping("/status")
   public ResponseEntity<String> status() {

      return ResponseEntity.ok("Rating service is up and running");
   }

   @PostMapping("/add")
   public ResponseEntity<String> addRating(
         @RequestParam String recipeId,
         @RequestParam String username,
         @RequestParam int rating,
         @RequestParam String createdAt) {

      Rating ratingObj = Rating.builder()
            .recipeId(recipeId)
            .username(username)
            .rating(rating)
            .createdAt(createdAt)
            .build();

      ratingServices.addRating(ratingObj);

      return ResponseEntity.ok("Rating added successfully");
   }

}
