package fr.uge.jee.reddit.topic;

import com.sun.net.httpserver.Headers;
import fr.uge.jee.reddit.auth.AuthErrorResponse;
import fr.uge.jee.reddit.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.headers.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;

@RestController
@RequestMapping("/api/topics")
@Tag(name = "topics", description = "The topics API.")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Operation(summary = "create a topic.", tags = { "topics" })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successful operation"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "author not found",
                    content = @Content(schema = @Schema(implementation = TopicErrorResponse.class))
            )
    })
    @PostMapping(value = "/create-topic", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createTopic(@Valid @RequestBody TopicCreateRequest request){
        if(!userService.existsByUsername(request.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new AuthErrorResponse("topic/user-not-found","No user with this username!"));
        }

        topicService.save(
                new Topic(
                        request.getTitle(),
                        request.getContent(),
                        userService.findByUsername(request.getUsername()).get(),
                        0,
                        0,
                        0,
                        new Date(System.currentTimeMillis()),
                        null
                )
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
