package cl.govegan.ms_comment_rating.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import cl.govegan.ms_comment_rating.models.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

    Page<Comment> findByRecipeId(String recipeId, Pageable pageable);

    Page<Comment> findByUsername(String username, Pageable pageable);

    Page<Comment> findByUsernameAndRecipeId(String username, String recipeId, Pageable pageable);

    long deleteByUsernameAndRecipeId(String username, String recipeId);

}
 