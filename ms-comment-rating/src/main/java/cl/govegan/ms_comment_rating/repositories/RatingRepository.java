package cl.govegan.ms_comment_rating.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import cl.govegan.ms_comment_rating.models.Rating;

public interface RatingRepository extends MongoRepository<Rating, String>{

   @Override
   Optional<Rating> findById(String id);
   
   List<Rating> findByRecipeId(String recipeId);
   List<Rating> findByUsername(String username);
   List<Rating> findByUsernameAndRecipeId(String username, String recipeId);

   /* Estos métodos de búsqueda */
}
