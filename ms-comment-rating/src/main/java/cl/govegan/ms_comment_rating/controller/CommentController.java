package cl.govegan.ms_comment_rating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return ResponseEntity.ok("Exit");

    }
}
