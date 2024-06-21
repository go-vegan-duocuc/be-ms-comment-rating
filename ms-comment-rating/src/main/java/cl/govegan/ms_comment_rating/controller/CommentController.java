package cl.govegan.ms_comment_rating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<Comment>> findCommentsByRecipeId(
            @RequestParam String recipeId,
            @RequestParam int page,
            @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> comments = commentService.findByRecipeId(recipeId, pageable);
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/findByUsername")
    public ResponseEntity<Page<Comment>> findCommentsByUsername(
            @RequestParam String username,
            @RequestParam int page,
            @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> comments = commentService.findByUsername(username, pageable);
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/findByUsernameAndRecipeId")
    public ResponseEntity<Page<Comment>> findCommentsByUsernameAndRecipeId(
            @RequestParam String username,
            @RequestParam String recipeId,
            @RequestParam int page,
            @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> comments = commentService.findByUsernameAndRecipeId(username, recipeId, pageable);
        return ResponseEntity.ok(comments);
                
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCommentByUsernameAndRecipeId(
            @RequestParam String recipeId,
            @RequestParam String username) {
        boolean isDeleted = commentService.deleteCommentbyUsernameAndRecipeId(recipeId, username);
        if (isDeleted) {
            return ResponseEntity.ok("Comments deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No comments found to delete for the given parameters.");
        }
    }

}


