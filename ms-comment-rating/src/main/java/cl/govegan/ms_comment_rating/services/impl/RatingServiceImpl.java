package cl.govegan.ms_comment_rating.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.govegan.ms_comment_rating.models.Rating;
import cl.govegan.ms_comment_rating.repositories.RatingRepository;
import cl.govegan.ms_comment_rating.services.RatingServices;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingServices{

   private final RatingRepository ratingRepository;

   @Override
   public Rating findById(String id) {
      return ratingRepository.findById(id).orElse(null);
   }

   @Override
    public Page<Rating> findByRecipeId(String recipeId, Pageable pageable) {
        return ratingRepository.findByRecipeId(recipeId, pageable);
    }

    @Override
    public Page<Rating> findByUsername(String username, Pageable pageable) {
        return ratingRepository.findByUsername(username, pageable);
    }

    @Override
    public Page<Rating> findByUsernameAndRecipeId(String username, String recipeId, Pageable pageable) {
        return ratingRepository.findByUsernameAndRecipeId(username, recipeId, pageable);
    }

    @Override
    public Rating addRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(String id) {
        ratingRepository.deleteById(id);
    }
   
    @Override
    public double getAverageRatingByRecipeId(String recipeId) {
        return ratingRepository.findAverageRatingByRecipeId(recipeId);
    }
    
}
