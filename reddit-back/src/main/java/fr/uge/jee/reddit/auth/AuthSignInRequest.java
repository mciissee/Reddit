package fr.uge.jee.reddit.auth;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

/**
 *  Representation of the sign in request body object.
 */
public class AuthSignInRequest {

    @NotBlank
    @Schema(description = "Username of the user to sign in.", required = true)
    private String username;

    @NotBlank
    @Schema(description = "Password of the user to sign in.", required = true)
    private String password;

    public AuthSignInRequest() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
