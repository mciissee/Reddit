package fr.uge.jee.reddit.post.topic;

import fr.uge.jee.reddit.auth.AuthService;
import fr.uge.jee.reddit.user.UserRole;
import fr.uge.jee.reddit.utils.RestErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/topics")
@Tag(name = "topics", description = "The topics API.")
public class TopicController {
    @Autowired
    private AuthService authService;

    @Autowired
    private TopicService topicService;

    @Operation(summary = "create a topic.", tags = { "topics" })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = TopicDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "user not connected",
                    content = @Content(schema = @Schema(implementation = RestErrorResponse.class))
            )
    })
    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> create(@Valid @RequestBody TopicCreateRequest request){
        var maybeUser = authService.currentUser();
        if (maybeUser.isEmpty()) {
            return RestErrorResponse.notFound("user not connected");
        }

        var topic = topicService.create(
                maybeUser.get(),
                request.getTitle(),
                request.getContent()
        );

        return ResponseEntity.ok(new TopicDTO(topic));
    }

    @Operation(summary = "delete a topic.", tags = { "comments" })
    @DeleteMapping(value ="/{postId}")
    public ResponseEntity<?> delete(@PathVariable("postId") long postId) {
        var maybeUser = authService.currentUser();
        if (maybeUser.isEmpty()) {
            return RestErrorResponse.unauthorized("user not connected");
        }

        if (!maybeUser.get().getRole().equals(UserRole.ADMIN)) {
            return RestErrorResponse.unauthorized("only admin users can delete topics");
        }

        topicService.delete(postId);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }

    @Operation(summary = "paginate through topic collection ", tags = { "topics" })
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Successful operation",
                content = @Content(schema = @Schema(implementation = Page.class))
        ),
    })
    @GetMapping(value = "/")
    public ResponseEntity<?> paginate(Pageable pageable) {
        return ResponseEntity.ok(
            topicService.paginate(pageable).map(TopicDTO::new)
        );
    }

    @Operation(summary = "find a topic by its id.", tags = { "topics" })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable(name="id") long id){
        var topic = topicService.findById(id);
        if(topic.isEmpty()) {
            return RestErrorResponse.notFound("topic not found");
        }

        return ResponseEntity.ok(new TopicDTO(topic.get()));
    }
}
