package cl.govegan.ms_comment_rating.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/find")
     public ResponseEntity<List<Comment>> findComments(
            @RequestParam(required = false) String recipeId,
            @RequestParam(required = false) String username) {
        
        Set<Comment> comments = new HashSet<>();

        if (recipeId != null && !recipeId.isEmpty()) {
            comments.addAll(commentService.findByRecipeId(recipeId));
        }
        if (username != null && !username.isEmpty()) {
            comments.addAll(commentService.findByUsername(username));
        }
        if (recipeId != null && !recipeId.isEmpty() && username != null && !username.isEmpty()) {
            comments.addAll(commentService.findByUsernameAndRecipeId(username, recipeId));
        }

        List<Comment> uniqueComments = comments.stream().collect(Collectors.toList());

        return ResponseEntity.ok(uniqueComments);
    }
   
}
    

