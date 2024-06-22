package cl.govegan.ms_comment_rating.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.govegan.ms_comment_rating.models.Comment;
import org.springframework.stereotype.Service;

@Service
public interface CommentServices {

    Comment findById(String id);
    Page<Comment> findByRecipeId(String recipeId, Pageable pageable);
    Page<Comment> findByUsername(String username, Pageable pageable);
    Page<Comment> findByUsernameAndRecipeId(String username, String recipeId, Pageable pageable);
    Comment addComment(Comment comment);
    Comment updateComment(Comment comment);
    boolean deleteCommentbyUsernameAndRecipeId(String recipeId, String username);
}
