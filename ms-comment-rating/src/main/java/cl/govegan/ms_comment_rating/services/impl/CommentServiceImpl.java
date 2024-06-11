package cl.govegan.ms_comment_rating.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.govegan.ms_comment_rating.models.Comment;
import cl.govegan.ms_comment_rating.repositories.CommentRepository;
import cl.govegan.ms_comment_rating.services.CommentServices;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentServices {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findByRecipeId(String recipeId) {
        return commentRepository.findByRecipeId(recipeId);
    }

    @Override
    public List<Comment> findByUsername(String username) {
        return commentRepository.findByUsername(username);
    }

    @Override
    public List<Comment> findByUsernameAndRecipeId(String username, String recipeId) {
        return commentRepository.findByUsernameAndRecipeId(username, recipeId);
    }
    
    @Override
    public void deleteCommentbyUsernameAndRecipeId(String username, String recipeId) {
        commentRepository.deleteCommentByUsernameAndRecipeId(username, recipeId);
    }
    
}
