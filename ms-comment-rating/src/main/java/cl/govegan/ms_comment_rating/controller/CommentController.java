package cl.govegan.ms_comment_rating.controller;

import cl.govegan.ms_comment_rating.dto.CommentRequest;
import cl.govegan.ms_comment_rating.models.Comment;
import cl.govegan.ms_comment_rating.services.CommentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
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

    @PostMapping("/findByUsernameAndRecipeId")
    public ResponseEntity<Page<Comment>> findCommentsByUsernameAndRecipeId(@RequestBody CommentRequest commentRequest) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Page<Comment> comments = commentService.findByUsernameAndRecipeId(
                commentRequest.getUsername(),
                commentRequest.getRecipeId(),
                PageRequest.of(commentRequest.getPage(), commentRequest.getSize(), sort)
        );
        return ResponseEntity.ok(comments);
    }
  /*
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCommentByUsernameAndRecipeId(
            @RequestParam String recipeId,
            @RequestParam String username) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUsername = authentication.getName();

        if (!currentUsername.equals(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You can only delete your own comments.");
        }

        boolean isDeleted = commentService.deleteCommentbyUsernameAndRecipeId(recipeId, username);
        if (isDeleted) {
            return ResponseEntity.ok("Comments deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No comments found to delete for the given parameters.");
        }
*/
    public ResponseEntity<String> deleteCommentByUsernameAndRecipeId(@RequestBody CommentRequest commentRequest) {
        commentService.deleteCommentbyUsernameAndRecipeId(commentRequest.getRecipeId(), commentRequest.getUsername());
        return ResponseEntity.ok("Comments deleted successfully.");

    }
    

}


