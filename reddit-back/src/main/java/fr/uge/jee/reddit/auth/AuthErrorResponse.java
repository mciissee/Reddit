package fr.uge.jee.reddit.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Representation of an error response of the auth api.
 */
@Getter
@Setter
public class AuthErrorResponse {

    @NotBlank
    @Schema(description = "Unique identifier of the error.", required = true)
    private String code;

    @NotBlank
    @Schema(description = "Human readable string describing the error.", required = true)
    private String message;

    public AuthErrorResponse() {}

    /**
     * Builds new AuthError object.
     * @param code Unique identifier of the error.
     * @param message Human readable string describing the error.
     */
    public AuthErrorResponse(@NotBlank String code, @NotBlank String message) {
        this.code = code;
        this.message = message;
    }
}
