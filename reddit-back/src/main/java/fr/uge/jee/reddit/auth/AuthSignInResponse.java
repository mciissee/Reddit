package fr.uge.jee.reddit.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 *  Representation of the sign in request response object.
 */
@Getter
@Setter
public class AuthSignInResponse {

    @NotBlank
    @Schema(description = "Json Web token used to authenticate the user.", required = true)
    private String token;

    public AuthSignInResponse() {}

    public AuthSignInResponse(String token) {
        this.token = Objects.requireNonNull(token);
    }
}
