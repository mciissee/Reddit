package fr.uge.jee.reddit.auth;

import fr.uge.jee.reddit.security.JwtUtils;
import fr.uge.jee.reddit.user.User;
import fr.uge.jee.reddit.user.UserRole;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Authentication API controller.
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "auth", description = "The authentication API.")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(summary = "Sign in an user.", tags = { "auth" })
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = AuthSignInResponse.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "auth/unauthorized",
            content = @Content(schema = @Schema(implementation = AuthErrorResponse.class))
        )
    })
    @PostMapping(value = "/sign-in", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> signIn(@Valid @RequestBody AuthSignInRequest request) {
        try {
            var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok(new AuthSignInResponse(jwtUtils.create(authentication)));
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new AuthErrorResponse("auth/unauthorized","There is no user record with the given credentials."));
        }
    }

    @Operation(summary = "Sign up an user.", tags = { "auth" })
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Successful operation"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "auth/username-already-used | auth/email-already-used",
            content = @Content(schema = @Schema(implementation = AuthErrorResponse.class))
        )
    })
    @PostMapping(value = "/sign-up", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> signUp(@Valid @RequestBody AuthSignUpRequest request) {
        if (userService.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new AuthErrorResponse("auth/email-already-used","Email is already taken!"));
        }

        if (userService.existsByUsername(request.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new AuthErrorResponse("auth/username-already-used","Username is already taken!"));
        }

        userService.save(
            new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                UserRole.USER
            )
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}