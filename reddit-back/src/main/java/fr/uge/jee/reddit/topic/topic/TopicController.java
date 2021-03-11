package fr.uge.jee.reddit.topic.topic;

import fr.uge.jee.reddit.auth.AuthErrorResponse;
import fr.uge.jee.reddit.user.User;
import fr.uge.jee.reddit.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/topics")
@Tag(name = "topics", description = "The topics API.")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

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
                    content = @Content(schema = @Schema(implementation = TopicCreateResponse.class))

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "author not found",
                    content = @Content(schema = @Schema(implementation = TopicErrorResponse.class))
            )
    })
    @PostMapping(value = "/create-topic", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createTopic(@Valid @RequestBody TopicCreateRequest request){
        var opt = currentUser();
        if (opt.isPresent()) {
            User user = opt.get();
            var topic = topicService.save(
                new Topic(
                    request.getTitle(),
                    request.getContent(),
                    user,
                    0,
                    0,
                    0,
                    new Date(System.currentTimeMillis()),
                    null
                )
            );
            return ResponseEntity.ok(new TopicCreateResponse(topic.getId()));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthErrorResponse("auth/unauthorized","user not connected"));
        }
    }

    @Operation(summary = "find a topic by his id.", tags = { "topics" })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = TopicFindByIdResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "topic not found",
                    content = @Content(schema = @Schema(implementation = TopicErrorResponse.class))
            )
    })
    @PostMapping(value = "/findTopicById", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> findById(@Valid @RequestBody TopicFindByIdRequest request){
        var topic = topicService.findById(request.getId());
        if(topic.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new TopicErrorResponse("topic/not-found","topic not found"));
        return ResponseEntity.ok(new TopicFindByIdResponse(topic.get()));
    }
}
