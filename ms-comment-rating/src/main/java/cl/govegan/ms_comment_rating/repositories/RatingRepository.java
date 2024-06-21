package cl.govegan.ms_comment_rating.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import cl.govegan.ms_comment_rating.models.Rating;
public interface RatingRepository extends MongoRepository<Rating, String>{

   @Override
   Optional<Rating> findById(String id);
   Page<Rating> findByRecipeId(String recipeId, Pageable pageable);
   Page<Rating> findByUsername(String username, Pageable pageable);
   Page<Rating> findByUsernameAndRecipeId(String username, String recipeId, Pageable pageable);

   /* Estos métodos de búsqueda */
}
