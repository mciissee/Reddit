package fr.uge.jee.reddit.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *  Representation of the sign up request body object.
 */
@Getter
@Setter
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
}
