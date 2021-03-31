package fr.uge.jee.reddit.topic.topic;

import fr.uge.jee.reddit.auth.AuthErrorResponse;
import fr.uge.jee.reddit.auth.AuthService;
import fr.uge.jee.reddit.topic.post.PostFactory;
import fr.uge.jee.reddit.topic.vote.VoteService;
import fr.uge.jee.reddit.topic.post.ErrorResponse;
import fr.uge.jee.reddit.topic.post.Post;
import fr.uge.jee.reddit.topic.post.PostService;
import fr.uge.jee.reddit.user.User;
import fr.uge.jee.reddit.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/topics")
@Tag(name = "topics", description = "The topics API.")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private PostService postService;


    @GetMapping(value = "/")
    public ResponseEntity<?> findAllTopicsOrderedByLikeDesc(Pageable pageable){
        return ResponseEntity.ok(topicService.findAllByOrderByLikeDesc(pageable));
    }


    @Operation(summary = "create a topic.", tags = { "topics" })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = TopicResponse.class))

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "author not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createTopic(@Valid @RequestBody TopicCreateRequest request){
        var opt = authService.currentUser();
        if (opt.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthErrorResponse("auth/unauthorized","user not connected"));
        }

        User user = opt.get();
        var post = postService.save(PostFactory.createPost(request.getContent(), user));
        var topic = topicService.save(
                new Topic(
                        request.getTitle(),
                        post
                )
        );
        return ResponseEntity.ok(new TopicResponse(topic));

    }

    @Operation(summary = "find a topic by its id.", tags = { "topics" })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable(name="id") long id){
        var topic = topicService.findById(id);
        if(topic.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("topic/not-found","topic not found"));
        return ResponseEntity.ok(new TopicResponse(topic.get()));
    }
}
