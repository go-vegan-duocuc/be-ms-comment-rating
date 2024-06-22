package cl.govegan.ms_comment_rating.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.govegan.ms_comment_rating.dto.RatingRequest;
import cl.govegan.ms_comment_rating.models.Rating;
import cl.govegan.ms_comment_rating.services.RatingServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/ratings")
@RequiredArgsConstructor
public class RatingController {

   
   private final RatingServices ratingServices;

   @GetMapping("/status")
   public ResponseEntity<String> status() {
      return ResponseEntity.ok("Rating service is up and running");
   }

   @PostMapping("/add")
    public ResponseEntity<String> addRating(@RequestBody Rating rating) {
        ratingServices.addRating(rating);
        return ResponseEntity.ok("Rating added successfully");
    }

   @PatchMapping("/update")
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

    @PostMapping("/findByRecipeId")
    public ResponseEntity<Page<Rating>> findRatingsByRecipeId(@RequestBody RatingRequest ratingRequest) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Page<Rating> ratings = ratingServices.findByRecipeId(
                ratingRequest.getRecipeId(),
                PageRequest.of(ratingRequest.getPage(), ratingRequest.getSize(), sort)
        );
        return ResponseEntity.ok(ratings);
    }

    @PostMapping("/findByRecipeIdAndUsername")
    public ResponseEntity<Page<Rating>> findByRecipeIdAndUsername(@RequestBody RatingRequest ratingRequest) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Page<Rating> ratings = ratingServices.findByUsernameAndRecipeId(
                ratingRequest.getUsername(),
                ratingRequest.getRecipeId(),
                PageRequest.of(ratingRequest.getPage(), ratingRequest.getSize(), sort)
        );
        return ResponseEntity.ok(ratings);
    }
    @GetMapping("/average")
    public ResponseEntity<String> getAverageRating(@RequestParam String recipeId) {
        double averageRating = ratingServices.getAverageRatingByRecipeId(recipeId);
        String roundedAverage = String.format("%.2f", Math.round(averageRating * 100.0) / 100.0);
        return ResponseEntity.ok(roundedAverage);
    }
}
