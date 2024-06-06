package cl.govegan.ms_comment_rating.services.impl;

import java.util.List;

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
   public List<Rating> findByRecipeId(String recipeId) {
      return ratingRepository.findByRecipeId(recipeId);
   }

   @Override
   public List<Rating> findByUsername(String username) {
      return ratingRepository.findByUsername(username);
   }

   @Override
   public List<Rating> findByUsernameAndRecipeId(String username, String recipeId) {
      return ratingRepository.findByUsernameAndRecipeId(username, recipeId);
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
   
}
