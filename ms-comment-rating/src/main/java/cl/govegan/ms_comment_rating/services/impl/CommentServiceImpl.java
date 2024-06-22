package cl.govegan.ms_comment_rating.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.govegan.ms_comment_rating.exception.ResourceNotFoundException;
import cl.govegan.ms_comment_rating.models.Comment;
import cl.govegan.ms_comment_rating.repositories.CommentRepository;
import cl.govegan.ms_comment_rating.services.CommentServices;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentServices {
    
    private final CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }
    @Override
    public Comment findById(String id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Comment> findByRecipeId(String recipeId, Pageable pageable) {
        return commentRepository.findByRecipeId(recipeId, pageable);
    }

    @Override
    public Page<Comment> findByUsername(String username, Pageable pageable) {
        return commentRepository.findByUsername(username, pageable);
    }

    @Override
    public Page<Comment> findByUsernameAndRecipeId(String username, String recipeId, Pageable pageable) {
        return commentRepository.findByUsernameAndRecipeId(username, recipeId, pageable);
    }
    
    @Override
    public boolean deleteCommentbyUsernameAndRecipeId(String recipeId, String username) {
        long deletedCount = commentRepository.deleteByUsernameAndRecipeId(recipeId, username);
        if (deletedCount == 0) {
            throw new ResourceNotFoundException("No comments found to delete for the given parameters.");
        }
        return false;
    }
    @Override
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

}
