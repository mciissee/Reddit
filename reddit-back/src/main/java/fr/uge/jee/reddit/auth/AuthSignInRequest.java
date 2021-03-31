package fr.uge.jee.reddit.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 *  Representation of the sign in request body object.
 */
@Getter
@Setter
public class AuthSignInRequest {

    @NotBlank
    @Schema(description = "Username of the user to sign in.", required = true)
    private String username;

    @NotBlank
    @Schema(description = "Password of the user to sign in.", required = true)
    private String password;

    public AuthSignInRequest() {}
}
