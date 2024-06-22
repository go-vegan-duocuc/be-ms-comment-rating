package cl.govegan.ms_comment_rating.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.govegan.ms_comment_rating.models.Rating;

@Service

public interface RatingServices {

      public Rating findById(String id);
      public Page<Rating> findByRecipeId(String recipeId, Pageable pageable);
      public Page<Rating> findByUsername(String username, Pageable pageable);
      public Page<Rating> findByUsernameAndRecipeId( String recipeId ,String username, Pageable pageable);
      Rating addRating(Rating rating);
      Rating updateRating(Rating rating);
      void deleteRating(String id);
      double getAverageRatingByRecipeId(String recipeId);
}

/* Servicio comentarios 

Idencontrar por id -> uno
por receta -> varios (Lista)
por usuario
por usuario y receta
agregar comentario
actualizar comentario
eliminar comentario
*/
