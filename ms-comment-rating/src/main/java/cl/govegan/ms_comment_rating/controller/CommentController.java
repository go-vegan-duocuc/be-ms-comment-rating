package cl.govegan.ms_comment_rating.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

   @GetMapping("/status")
   public ResponseEntity<String> status() {
      return ResponseEntity.ok("Comment service is up and running");
   }
   
}
