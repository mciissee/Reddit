package fr.uge.jee.reddit.topic.comment;

import fr.uge.jee.reddit.auth.AuthErrorResponse;
import fr.uge.jee.reddit.topic.like.Like;
import fr.uge.jee.reddit.topic.like.LikeService;
import fr.uge.jee.reddit.topic.topic.Topic;
import fr.uge.jee.reddit.topic.topic.TopicCreateResponse;
import fr.uge.jee.reddit.user.User;
import fr.uge.jee.reddit.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@Tag(name = "comments", description = "The comments API.")
public class CommentController {
    @Autowired
    private CommentService commentService;

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
    @Operation(summary = "create a comment.", tags = { "comments" })
    @PostMapping(value ="/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createComment(String content){
        var opt = currentUser();
        if (opt.isPresent()) {
            User user = opt.get();
            var comment = commentService.save(
                new Comment(
                        user,
                        content,
                        likeService.save(new Like(0, 0, 0, new ArrayList<>(), new ArrayList<>())),
                        new Date(System.currentTimeMillis()),
                        new ArrayList<>()
                )
            );
            return ResponseEntity.ok(comment);
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthErrorResponse("auth/unauthorized","user not connected"));
        }
    }
/*
    @Operation(summary = "find a comment.", tags = { "comments" })
    @GetMapping(value ="/{id}", consumes = "application/long", produces = "application/json")
*/
}
