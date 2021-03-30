package fr.uge.jee.reddit.topic.topic;

import fr.uge.jee.reddit.auth.AuthErrorResponse;
import fr.uge.jee.reddit.topic.comment.Comment;
import fr.uge.jee.reddit.topic.like.Like;
import fr.uge.jee.reddit.topic.like.LikeService;
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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/topics")
@Tag(name = "topics", description = "The topics API.")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    private Optional<User> currentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails details = (UserDetails) auth.getPrincipal();
            String userName = details.getUsername();
            return userService.findByUsername(userName);
        }
        return Optional.empty();
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
                    content = @Content(schema = @Schema(implementation = TopicErrorResponse.class))
            )
    })
    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createTopic(@Valid @RequestBody TopicCreateRequest request){
        var opt = currentUser();
        if (opt.isPresent()) {
            User user = opt.get();
            var topic = topicService.save(
                    new Topic(
                            request.getTitle(),
                            request.getContent(),
                            user,
                            new Date(System.currentTimeMillis()),
                            likeService.save(new Like(0, 0, 0, new ArrayList<>(), new ArrayList<>())),
                            new ArrayList<>()
                    )
            );
            return ResponseEntity.ok(new TopicResponse(topic));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthErrorResponse("auth/unauthorized","user not connected"));
        }
    }

    @Operation(summary = "find a topic by his id.", tags = { "topics" })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable(name="id") long id){
        var topic = topicService.findById(id);
        if(topic.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new TopicErrorResponse("topic/not-found","topic not found"));
        return ResponseEntity.ok(new TopicResponse(topic.get()));
    }

    @Operation(summary = "find a topic by his id and return the comments of the topics.", tags = { "topics" })
    @GetMapping(value = "/{id}/comments", produces = "application/json")
    public ResponseEntity<?> findById_comments(@PathVariable(name="id") long id){
        var topic = topicService.findById(id);
        if(topic.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new TopicErrorResponse("topic/not-found","topic not found"));
        return ResponseEntity.ok(new TopicResponse(topic.get()));
    }

    @Operation(summary = "find a topic by his id and return a specified comment in the comments of the topic.", tags = { "topics" })
    @GetMapping(value = "/{id}/comments/{commentId}", produces = "application/json")
    public ResponseEntity<?> findById_commentId(@PathVariable(name="id") long id, @PathVariable(name="commentId") long commentId){
        var topic = topicService.findById(id);
        if(topic.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new TopicErrorResponse("topic/not-found","topic not found"));
        Comment comment = new Comment();
        comment.setId(commentId);
        int i = topic.get().getCommentList().indexOf(comment);
        if(i == -1)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new TopicErrorResponse("comment/not-found","comment not found"));

        return ResponseEntity.ok(topic.get().getCommentList().get(i));
    }

    @Operation(summary = "find a topic by his id and return the like of the topic.", tags = { "topics" })
    @GetMapping(value = "/{id}/like", produces = "application/json")
    public ResponseEntity<?> findById_like(@PathVariable(name="id") long id){
        var topic = topicService.findById(id);
        if(topic.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new TopicErrorResponse("topic/not-found","topic not found"));
        return ResponseEntity.ok(topic.get().getLike());
    }

    @Operation(summary = "find a topic by his id and return the like of a specified comment.", tags = { "topics" })
    @GetMapping(value = "/{id}/comments/{commentId}/like", produces = "application/json")
    public ResponseEntity<?> findById_commentId_like(@PathVariable(name="id") long id, @PathVariable(name="commentId") long commentId){
        var topic = topicService.findById(id);
        if(topic.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new TopicErrorResponse("topic/not-found","topic not found"));
        Comment comment = new Comment();
        comment.setId(commentId);
        int i = topic.get().getCommentList().indexOf(comment);
        if(i == -1)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new TopicErrorResponse("comment/not-found","comment not found"));

        return ResponseEntity.ok(topic.get().getCommentList().get(i).getLike());
    }

    @Operation(summary = "find a topic by his id and return the like of a specified comment.", tags = { "topics" })
    @PatchMapping(value = "/{id}/like-up", produces = "application/json")
    public ResponseEntity<?> like(@PathVariable(name="id") long id){
        var opt = currentUser();
        if (opt.isPresent()) {
            User user = opt.get();
            var topic = topicService.findById(id);
            if(topic.isEmpty())
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new TopicErrorResponse("topic/not-found","topic not found"));
            topic.get().getLike().addLike(user);
            topicService.save(topic.get());
            return ResponseEntity.ok(topic.get());

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthErrorResponse("auth/unauthorized","User not connected."));
    }

    @Operation(summary = "find a topic by his id and return the like of a specified comment.", tags = { "topics" })
    @PatchMapping(value = "/{id}/like-down", produces = "application/json")
    public ResponseEntity<?> dislike(@PathVariable(name="id") long id){
        var opt = currentUser();
        if (opt.isPresent()) {
            User user = opt.get();
            var topic = topicService.findById(id);
            if(topic.isEmpty())
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new TopicErrorResponse("topic/not-found","topic not found"));
            topic.get().getLike().addDislike(user);
            topicService.save(topic.get());
            return ResponseEntity.ok(topic.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthErrorResponse("auth/unauthorized","User not connected."));
    }

    public ResponseEntity<?> findAllTopicsOrderedByLikeDesc(Pageable pageable){
        return ResponseEntity.ok(topicService.findAllByOrderByLikeDesc(pageable));
    }
}
