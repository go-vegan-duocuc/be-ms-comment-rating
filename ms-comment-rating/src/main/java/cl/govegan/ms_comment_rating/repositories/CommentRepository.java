package cl.govegan.ms_comment_rating.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import cl.govegan.ms_comment_rating.models.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

    @Override
    Optional<Comment> findById (String id);  

}
