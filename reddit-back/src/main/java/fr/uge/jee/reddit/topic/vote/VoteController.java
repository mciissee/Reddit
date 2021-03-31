package fr.uge.jee.reddit.topic.vote;

import fr.uge.jee.reddit.auth.AuthService;
import fr.uge.jee.reddit.topic.post.ErrorResponse;
import fr.uge.jee.reddit.topic.post.PostService;
import fr.uge.jee.reddit.user.UserService;
import fr.uge.jee.reddit.utils.RestErrorResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes")
@Tag(name = "topics", description = "The topics API.")
public class VoteController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private PostService postService;

    @PostMapping(value = "/{postId}", produces = "application/json")
    public ResponseEntity<?> createVote(@PathVariable(name="postId") long postId, VoteStatus vote){
        var opt = authService.currentUser();
        if (opt.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new RestErrorResponse("auth/unauthorized","user not connected"));
        }
        var post = postService.findById(postId);
        if(post.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("post/not_found","post not found"));
        return ResponseEntity.ok(voteService.save(new Vote(post.get(), opt.get(), vote)));
    }

    @GetMapping(value = "/{postId}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable(name="postId") long postId){
        var opt = authService.currentUser();
        if (opt.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new RestErrorResponse("auth/unauthorized","user not connected"));
        }
        var post = postService.findById(postId);
        if(post.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("post/not_found","post not found"));
        return ResponseEntity.ok(voteService.findAllByPost_id(postId));
    }
}
