package cl.govegan.ms_comment_rating.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.govegan.ms_comment_rating.models.Rating;

@Service
public interface RatingServices {

      public Rating findById(String id);
      public List<Rating> findByRecipeId(String recipeId);
      public List<Rating> findByUsername(String username);
      public List<Rating> findByUsernameAndRecipeId(String username, String recipeId);
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
