package fr.uge.jee.reddit.topic.comment;

import fr.uge.jee.reddit.auth.AuthErrorResponse;
import fr.uge.jee.reddit.auth.AuthService;
import fr.uge.jee.reddit.topic.post.ErrorResponse;
import fr.uge.jee.reddit.topic.post.Post;
import fr.uge.jee.reddit.topic.post.PostService;
import fr.uge.jee.reddit.topic.vote.Vote;
import fr.uge.jee.reddit.topic.vote.VoteService;
import fr.uge.jee.reddit.user.User;
import fr.uge.jee.reddit.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;

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
    private VoteService voteService;

    @Autowired
    private PostService postService;


    @Operation(summary = "create a comment.", tags = { "comments" })
    @PostMapping(value ="/{postId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createComment(@PathVariable("postId") long id, String content){
        var opt = authService.currentUser();
        if (opt.isPresent()) {
            User user = opt.get();
            var post = postService.findById(id);
            if(post.isEmpty())
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("post/not-found","post not found"));


            var comment = commentService.save(new Comment(post.get()));
            post.get().getComments().add(comment);
            postService.save(post.get());
            return ResponseEntity.ok(comment);
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthErrorResponse("auth/unauthorized","user not connected"));
        }
    }

    @Operation(summary = "find a comment.", tags = { "comments" })
    @GetMapping(value ="/{postId}", consumes = "application/long", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable("postId") long id){
        var comment = commentService.findById(id);
        if(comment.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new CommentResponse(comment.get()));
    }

/*
    @Operation(summary = "find a comment by his id and return the like of a specified comment.", tags = { "comments" })
    @GetMapping(value = "/{postId}/comments", produces = "application/json")
    public ResponseEntity<?> findById_commentId_like(@PathVariable(name="postId") long id, @PathVariable(name="subCommentId") long commentId){
        Comment comment = new Comment();
        comment.setId(commentId);
        int i = comment.getCommentList().indexOf(comment);
        if(i == -1)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new CommentErrorResponse("comment/not-found","comment not found"));

        return ResponseEntity.ok(comment.getCommentList().get(i).getLike());
    }
*/
}
