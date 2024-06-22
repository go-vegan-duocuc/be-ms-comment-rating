package cl.govegan.ms_comment_rating.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.govegan.ms_comment_rating.dto.CommentRequest;
import cl.govegan.ms_comment_rating.models.Comment;
import cl.govegan.ms_comment_rating.services.CommentServices;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

   
    private final CommentServices commentService;

   @GetMapping("/status")
   public ResponseEntity<String> status() {
      return ResponseEntity.ok("Comment service is up and running");
   }
   @PostMapping
    public ResponseEntity<String> createComment(@RequestBody Comment comment) {
      commentService.addComment(comment);
        return ResponseEntity.ok("Comment added correctly");

    }
    @PostMapping("/findByRecipeId")
    public ResponseEntity<Page<Comment>> findCommentsByRecipeId(@RequestBody CommentRequest commentRequest) {
        Page<Comment> comments = commentService.findByRecipeId(
                commentRequest.getRecipeId(),
                PageRequest.of(commentRequest.getPage(), commentRequest.getSize())
        );
        return ResponseEntity.ok(comments);
    }
    @PostMapping("/findByUsername")
    public ResponseEntity<Page<Comment>> findCommentsByUsername(@RequestBody CommentRequest commentRequest) {
        Page<Comment> comments = commentService.findByUsername(
                commentRequest.getUsername(),
                PageRequest.of(commentRequest.getPage(), commentRequest.getSize())
        );
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/findByUsernameAndRecipeId")
    public ResponseEntity<Page<Comment>> findCommentsByUsernameAndRecipeId(@RequestBody CommentRequest commentRequest) {
        Page<Comment> comments = commentService.findByUsernameAndRecipeId(
                commentRequest.getUsername(),
                commentRequest.getRecipeId(),
                PageRequest.of(commentRequest.getPage(), commentRequest.getSize())
        );
        return ResponseEntity.ok(comments);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCommentByUsernameAndRecipeId(@RequestBody CommentRequest commentRequest) {
        boolean isDeleted = commentService.deleteCommentbyUsernameAndRecipeId(commentRequest.getRecipeId(), commentRequest.getUsername());
        return isDeleted ? ResponseEntity.ok("Comments deleted successfully.")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No comments found to delete for the given parameters.");
    }
    

}


