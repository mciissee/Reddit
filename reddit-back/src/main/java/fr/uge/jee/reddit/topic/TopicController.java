package fr.uge.jee.reddit.topic;

import com.sun.net.httpserver.Headers;
import fr.uge.jee.reddit.auth.AuthErrorResponse;
import fr.uge.jee.reddit.user.User;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails details = (UserDetails) auth.getPrincipal();
            String userName = details.getUsername();
            Optional<User> opt = userService.findByUsername(userName);
            if (opt.isPresent()) {
                User user = opt.get();
                topicService.save(
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
            }
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthErrorResponse("auth/unauthorized","....."));
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
