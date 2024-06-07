package cl.govegan.ms_comment_rating.services;

import org.springframework.stereotype.Service;

import cl.govegan.ms_comment_rating.models.Comment;


@Service
public interface CommentServices {
    
    Comment addComment(Comment comment);
}
