package cl.govegan.ms_comment_rating.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

   
    private final CommentServices commentServices;

   @GetMapping("/status")
   public ResponseEntity<String> status() {
      return ResponseEntity.ok("Comment service is up and running");
   }
   @PostMapping("/add")
    public ResponseEntity<String> addComment(@RequestBody Comment comment) {
        commentServices.addComment(comment);
        return ResponseEntity.ok("Comment added successfully");
    }
    @PostMapping("/findByRecipeId")
    public ResponseEntity<Page<Comment>> findCommentsByRecipeId(@RequestBody CommentRequest commentRequest) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Page<Comment> comments = commentServices.findByRecipeId(
                commentRequest.getRecipeId(),
                PageRequest.of(commentRequest.getPage(), commentRequest.getSize(), sort)
        );
        return ResponseEntity.ok(comments);
    }
    @PostMapping("/findByUsername")
    public ResponseEntity<Page<Comment>> findCommentsByUsername(@RequestBody CommentRequest commentRequest) {
        Page<Comment> comments = commentServices.findByUsername(
                commentRequest.getUsername(),
                PageRequest.of(commentRequest.getPage(), commentRequest.getSize())
        );
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/findByUsernameAndRecipeId")
    public ResponseEntity<Page<Comment>> findCommentsByUsernameAndRecipeId(@RequestBody CommentRequest commentRequest) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Page<Comment> comments = commentServices.findByUsernameAndRecipeId(
                commentRequest.getUsername(),
                commentRequest.getRecipeId(),
                PageRequest.of(commentRequest.getPage(), commentRequest.getSize(), sort)
        );
        return ResponseEntity.ok(comments);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCommentByUsernameAndRecipeId(@RequestBody CommentRequest commentRequest) {
        commentServices.deleteCommentbyUsernameAndRecipeId(commentRequest.getRecipeId(), commentRequest.getUsername());
        return ResponseEntity.ok("Comments deleted successfully.");
    }
    

}


