package cl.govegan.ms_comment_rating.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.govegan.ms_comment_rating.models.Comment;
import cl.govegan.ms_comment_rating.services.CommentServices;


@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {


   @Autowired
    private CommentServices commentService;

   @GetMapping("/status")
   public ResponseEntity<String> status() {
      return ResponseEntity.ok("Comment service is up and running");
   }
   @PostMapping
    public ResponseEntity<String> createComment(@RequestBody Comment comment) {
      commentService.addComment(comment);
        return ResponseEntity.ok("Comment added correctly");

    }
    @GetMapping("/findByRecipeId")
public ResponseEntity<List<Comment>> findCommentsByRecipeId(@RequestParam String recipeId) {
    List<Comment> comments = commentService.findByRecipeId(recipeId);
    if (comments.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(Collections.emptyList());
    }
    return ResponseEntity.ok(comments);
}
    @GetMapping("/findByUsername")
public ResponseEntity<List<Comment>> findCommentsByUsername(@RequestParam String username) {
    List<Comment> comments = commentService.findByUsername(username);
    if (comments.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(Collections.emptyList());
    }
    return ResponseEntity.ok(comments);
}
    @GetMapping("/findByUsernameAndRecipeId")
public ResponseEntity<List<Comment>> findCommentsByUsernameAndRecipeId(@RequestParam String username, @RequestParam String recipeId) {
    List<Comment> comments = commentService.findByUsernameAndRecipeId(username, recipeId);
    if (comments.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(Collections.emptyList());
    }
    return ResponseEntity.ok(comments);
}

@DeleteMapping("/delete")
public ResponseEntity<String> deleteComment(
        @RequestParam String recipeId,
        @RequestParam String username) {

    List<Comment> comments = commentService.findByUsernameAndRecipeId(username, recipeId);
    
    if (comments.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("No comments found to delete for the given parameters.");
    }

    commentService.deleteCommentbyUsernameAndRecipeId(recipeId, username);
    return ResponseEntity.ok("Comments deleted successfully.");
}
}
    

