package fr.uge.jee.reddit.post.comment;

import fr.uge.jee.reddit.auth.AuthService;
import fr.uge.jee.reddit.post.PostService;
import fr.uge.jee.reddit.user.User;
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
    private AuthService authService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @Operation(summary = "create a comment.", tags = { "comments" })
    @PostMapping(value ="/{postId}/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> create(@PathVariable("postId") long postId, @RequestBody CommentCreateRequest request) {
        var maybeUser = authService.currentUser();
        if (maybeUser.isEmpty()) {
            return RestErrorResponse.unauthorized("user not connected");
        }
        User author = maybeUser.get();

        var maybePost = postService.findById(postId);
        if(maybePost.isEmpty()) {
            return RestErrorResponse.notFound("post not found");
        }
        var parent = maybePost.get();

        return ResponseEntity.ok(
            new CommentDTO(commentService.create(author, parent, request.getContent()))
        );
    }

    @Operation(summary = "delete a comment.", tags = { "comments" })
    @DeleteMapping(value ="/{postId}")
    public ResponseEntity<?> delete(@PathVariable("postId") long postId) {
        var maybeUser = authService.currentUser();
        if (maybeUser.isEmpty()) {
            return RestErrorResponse.unauthorized("user not connected");
        }

        commentService.delete(postId);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }

    @Operation(summary = "find the comments associated to a post", tags = { "comments" })
    @GetMapping(value = "/{postId}", produces = "application/json")
    public ResponseEntity<?> findAllOfPost(@PathVariable(name="postId") long postId){
        var maybePost = postService.findById(postId);
        if(maybePost.isEmpty()) {
            return RestErrorResponse.notFound("post not found");
        }

        var comments = commentService.findAllByParent(maybePost.get());
        return ResponseEntity.ok(
            comments.stream().map(CommentDTO::new).collect(Collectors.toList())
        );
    }
}
