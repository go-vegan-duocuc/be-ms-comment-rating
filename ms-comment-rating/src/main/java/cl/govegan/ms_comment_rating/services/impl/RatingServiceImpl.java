package cl.govegan.ms_comment_rating.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.govegan.ms_comment_rating.exceptions.ResourceNotFoundException;
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
        return ratingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found with id: " + id));
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
        if (!ratingRepository.existsById(rating.getId())) {
            throw new ResourceNotFoundException("Rating not found");
        }
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(String id) {
        if (!ratingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Rating not found");
        }
        ratingRepository.deleteById(id);
    }
   
    @Override
    public double getAverageRatingByRecipeId(String recipeId) {
        Double averageRating = ratingRepository.findAverageRatingByRecipeId(recipeId);
        if (averageRating == null) {
            throw new ResourceNotFoundException("No ratings found for recipeId: " + recipeId);
        }
        return averageRating;
    }
    
}
