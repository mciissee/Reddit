package fr.uge.jee.reddit.auth;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *  Representation of the sign up request body object.
 */
public class AuthSignUpRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    @Schema(description = "Username of the user to sign up.", required = true)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @Schema(description = "Email of the user to sign up.", required = true)
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    @Schema(description = "Password of the user to sign up.", required = true)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
