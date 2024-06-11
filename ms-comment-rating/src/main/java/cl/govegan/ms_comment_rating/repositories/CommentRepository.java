package cl.govegan.ms_comment_rating.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import cl.govegan.ms_comment_rating.models.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

    @Override
    Optional<Comment> findById (String id);  
    
   List<Comment> findByRecipeId(String recipeId);
   List<Comment> findByUsername(String username);
   List<Comment> findByUsernameAndRecipeId(String username, String recipeId);
   void deleteCommentByUsernameAndRecipeId(String username, String recipeId);
}
