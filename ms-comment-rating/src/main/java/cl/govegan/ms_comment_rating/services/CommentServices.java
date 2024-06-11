package cl.govegan.ms_comment_rating.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.govegan.ms_comment_rating.models.Comment;


@Service
public interface CommentServices {
    Comment addComment(Comment comment);
    List<Comment> findByRecipeId(String recipeId);
    List<Comment> findByUsername(String username);
    List<Comment> findByUsernameAndRecipeId(String username, String recipeId);
    void deleteCommentbyUsernameAndRecipeId(String username, String recipeId);
}
