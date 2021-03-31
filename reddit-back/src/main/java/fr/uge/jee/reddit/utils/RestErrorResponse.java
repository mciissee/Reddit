package fr.uge.jee.reddit.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Representation of an error response of the auth api.
 */
@Getter
@Setter
public class RestErrorResponse {

    @NotBlank
    @Schema(description = "Unique identifier of the error.", required = true)
    private String code;

    @NotBlank
    @Schema(description = "Human readable string describing the error.", required = true)
    private String message;

    public RestErrorResponse() {}

    /**
     * Builds new AuthError object.
     * @param code Unique identifier of the error.
     * @param message Human readable string describing the error.
     */
    public RestErrorResponse(@NotBlank String code, @NotBlank String message) {
        this.code = code;
        this.message = message;
    }
}
