package fr.uge.jee.reddit.post.vote;

import fr.uge.jee.reddit.auth.AuthService;
import fr.uge.jee.reddit.post.PostService;
import fr.uge.jee.reddit.utils.RestErrorResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/votes")
@Tag(name = "votes", description = "The votes API.")
public class VoteController {

    @Autowired
    private AuthService authService;
    @Autowired
    private PostService postService;
    @Autowired
    private VoteService voteService;

    @DeleteMapping(value ="/{postId}/unvote")
    public ResponseEntity<?> delete(@PathVariable(name="postId") long postId) {
        var user = authService.currentUser();
        if (user.isEmpty()) {
            return RestErrorResponse.unauthorized("user not connected");
        }

        var post = postService.findById(postId);
        if(post.isEmpty()) {
            return RestErrorResponse.notFound("post not found");
        }

        voteService.unvote(user.get(), post.get());
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }

    @GetMapping(
        value ="/{postId}/status",
        produces = "application/json"
    )
    public ResponseEntity<?> status(@PathVariable(name="postId") long postId) {
        var user = authService.currentUser();
        if (user.isEmpty()) {
            return RestErrorResponse.unauthorized("user not connected");
        }

        var post = postService.findById(postId);
        if(post.isEmpty()) {
            return RestErrorResponse.notFound("post not found");
        }

        var vote = voteService.find(user.get(), post.get());
        if (vote.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not voted");
        }

        return ResponseEntity.ok(new VoteDTO(vote.get()));
    }

    @PostMapping(
        value ="{postId}/upvote",
        produces = "application/json"
    )
    public ResponseEntity<?> upvote(@PathVariable(name="postId") long postId) {
        return createVote(postId, VoteTypes.UP_VOTE);
    }

    @PostMapping(
        value ="{postId}/downvote",
        produces = "application/json"
    )
    public ResponseEntity<?> downvote(@PathVariable(name="postId") long postId) {
        return createVote(postId, VoteTypes.DOWN_VOTE);
    }

    @GetMapping(
        value ="/{postId}",
        produces = "application/json"
    )
    public ResponseEntity<?> listVotes(@PathVariable(name="postId") long postId) {
        var user = authService.currentUser();
        if (user.isEmpty()) {
            return RestErrorResponse.unauthorized("user not connected");
        }

        var post = postService.findById(postId);
        if(post.isEmpty()) {
            return RestErrorResponse.notFound("post not found");
        }

        List<VoteDTO> votes = voteService.findAllOfPost(postId)
                .stream()
                .map(VoteDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(votes);
    }


    private ResponseEntity<?> createVote(long postId, VoteTypes type) {
        var user = authService.currentUser();
        if (user.isEmpty()) {
            return RestErrorResponse.unauthorized("user not connected");
        }
        var post = postService.findById(postId);
        if(post.isEmpty()) {
            return RestErrorResponse.notFound("post not found");
        }

        if (type.equals(VoteTypes.UP_VOTE)) {
            return ResponseEntity.ok(
                new VoteDTO(voteService.upvote(user.get(), post.get()))
            );
        }

        return ResponseEntity.ok(
            new VoteDTO(voteService.downvote(user.get(), post.get()))
        );
    }
}
