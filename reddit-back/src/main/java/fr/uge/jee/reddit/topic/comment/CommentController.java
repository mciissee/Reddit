package fr.uge.jee.reddit.topic.comment;

import fr.uge.jee.reddit.auth.AuthErrorResponse;
import fr.uge.jee.reddit.auth.AuthService;
import fr.uge.jee.reddit.topic.like.Like;
import fr.uge.jee.reddit.topic.like.LikeService;
import fr.uge.jee.reddit.user.User;
import fr.uge.jee.reddit.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@Tag(name = "comments", description = "The comments API.")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;


    @Operation(summary = "create a comment.", tags = { "comments" })
    @PostMapping(value ="/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createComment(String content){
        var opt = authService.currentUser();
        if (opt.isPresent()) {
            User user = opt.get();
            var comment = commentService.save(
                new Comment(
                        user,
                        content,
                        likeService.save(new Like(0, 0, 0, new ArrayList<>(), new ArrayList<>())),
                        new Date(System.currentTimeMillis()),
                        new ArrayList<>()
                )
            );
            return ResponseEntity.ok(comment);
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthErrorResponse("auth/unauthorized","user not connected"));
        }
    }

    @Operation(summary = "find a comment.", tags = { "comments" })
    @GetMapping(value ="/{id}", consumes = "application/long", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable("id") long id){
        var comment = commentService.findById(id);
        if(comment.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new CommentResponse(comment.get()));
    }

    @Operation(summary = "find a comment by his id and return the like of the comment.", tags = { "comments" })
    @GetMapping(value = "/{id}/like", produces = "application/json")
    public ResponseEntity<?> findById_like(@PathVariable(name="id") long id){
        var comment = commentService.findById(id);
        if(comment.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new CommentErrorResponse("comment/not-found","comment not found"));
        return ResponseEntity.ok(comment.get().getLike());
    }

    @Operation(summary = "find a comment by his id and return the like of a specified comment.", tags = { "comments" })
    @GetMapping(value = "/{id}/comments/{subCommentId}/like", produces = "application/json")
    public ResponseEntity<?> findById_commentId_like(@PathVariable(name="id") long id, @PathVariable(name="subCommentId") long commentId){
        Comment comment = new Comment();
        comment.setId(commentId);
        int i = comment.getCommentList().indexOf(comment);
        if(i == -1)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new CommentErrorResponse("comment/not-found","comment not found"));

        return ResponseEntity.ok(comment.getCommentList().get(i).getLike());
    }

    @Operation(summary = "find a comment by his id and return the like of a specified comment.", tags = { "comments" })
    @PatchMapping(value = "/{id}/like-up", produces = "application/json")
    public ResponseEntity<?> like(@PathVariable(name="id") long id){
        var opt = authService.currentUser();
        if (opt.isPresent()) {
            User user = opt.get();
            var comment = commentService.findById(id);
            if(comment.isEmpty())
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new CommentErrorResponse("comment/not-found","comment not found"));
            return ResponseEntity.ok(comment.get().getLike().addLike(user));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthErrorResponse("auth/unauthorized","User not connected."));
    }

    @Operation(summary = "find a comment by his id and return the like of a specified comment.", tags = { "comments" })
    @PatchMapping(value = "/{id}/like-down", produces = "application/json")
    public ResponseEntity<?> dislike(@PathVariable(name="id") long id){
        var opt = authService.currentUser();
        if (opt.isPresent()) {
            User user = opt.get();
            var comment = commentService.findById(id);
            if(comment.isEmpty())
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new CommentErrorResponse("comment/not-found","comment not found"));
            return ResponseEntity.ok(comment.get().getLike().addDislike(user));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthErrorResponse("auth/unauthorized","User not connected."));
    }
}
