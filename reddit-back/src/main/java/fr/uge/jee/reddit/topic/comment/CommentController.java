package fr.uge.jee.reddit.topic.comment;

import fr.uge.jee.reddit.auth.AuthService;
import fr.uge.jee.reddit.topic.post.ErrorResponse;
import fr.uge.jee.reddit.topic.post.PostFactory;
import fr.uge.jee.reddit.topic.post.PostService;
import fr.uge.jee.reddit.topic.vote.VoteService;
import fr.uge.jee.reddit.user.User;
import fr.uge.jee.reddit.user.UserService;
import fr.uge.jee.reddit.utils.RestErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

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
    public ResponseEntity<?> createComment(@PathVariable("postId") long postId, String content){
        var opt = authService.currentUser();
        if (opt.isPresent()) {
            User user = opt.get();
            var maybePost = postService.findById(postId);
            if(maybePost.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("post/not-found", "post not found"));
            }
            var parentPost = maybePost.get();
            var post = PostFactory.createPost(content, user);

            postService.save(post);
            var comment = commentService.save(new Comment(post));
            parentPost.setComments(parentPost.getComments() + 1);

            postService.save(parentPost);
            return ResponseEntity.ok(comment);
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new RestErrorResponse("auth/unauthorized","user not connected"));
        }
    }

    @Operation(summary = "find a comment by his id and return the like of a specified comment.", tags = { "comments" })
    @GetMapping(value = "/{postId}/comments", produces = "application/json")
    public ResponseEntity<?> findAllComments(@PathVariable(name="postId") long postId){
        var maybePost = postService.findById(postId);
        if(maybePost.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("post/not-found", "post not found"));
        }

        var comments = commentService.findAllByParent(maybePost.get());
        return ResponseEntity.ok(comments.stream().map(CommentDTO::new).collect(Collectors.toList()));
    }
}
