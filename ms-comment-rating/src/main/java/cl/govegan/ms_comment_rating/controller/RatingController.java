package cl.govegan.ms_comment_rating.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

   @PutMapping("/update")
    public ResponseEntity<String> updateRating(@RequestBody Rating rating) {
        Rating existingRating = ratingServices.findById(rating.getId());
        if (existingRating == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rating not found");
        }
        ratingServices.updateRating(rating);
        return ResponseEntity.ok("Rating updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRating(@RequestParam String id) {
        Rating existingRating = ratingServices.findById(id);
        if (existingRating == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rating not found");
        }
        ratingServices.deleteRating(id);
        return ResponseEntity.ok("Rating deleted successfully");
    }

    @GetMapping("/findByRecipeId")
    public ResponseEntity<Page<Rating>> findRatingsByRecipeId(
            @RequestParam String recipeId,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String sortDirection) {
        
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Rating> ratings = ratingServices.findByRecipeId(recipeId, pageable);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/findByRecipeIdAndUsername")
    public ResponseEntity<Page<Rating>> findByRecipeIdAndUsername(
            @RequestParam String recipeId, 
            @RequestParam String username, 
            @RequestParam int page, 
            @RequestParam int size,
            @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String sortDirection) {
        
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Rating> ratings = ratingServices.findByUsernameAndRecipeId(recipeId, username, pageable);
        return ResponseEntity.ok(ratings);
}
    @GetMapping("/average")
    public ResponseEntity<Double> getAverageRating(@RequestParam String recipeId) {
        double averageRating = ratingServices.getAverageRatingByRecipeId(recipeId);
        return ResponseEntity.ok(averageRating);
    }
}
