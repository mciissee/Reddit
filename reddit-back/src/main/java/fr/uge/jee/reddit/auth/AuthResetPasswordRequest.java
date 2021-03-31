package fr.uge.jee.reddit.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthResetPasswordRequest {

    @NotBlank
    @Size(min = 4, max = 40)
    @Schema(description = "Old password of the user to change.", required = true)
    private String oldPassword;

    @NotBlank
    @Size(min = 4, max = 40)
    @Schema(description = "New password of the user to change.", required = true)
    private String newPassword;

    public AuthResetPasswordRequest() {}
}
