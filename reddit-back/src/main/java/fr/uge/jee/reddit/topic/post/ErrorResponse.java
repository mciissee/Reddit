package fr.uge.jee.reddit.topic.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class ErrorResponse {

    @NotBlank
    @Schema(description = "Unique identifier of the error.", required = true)
    private String code;

    @NotBlank
    @Schema(description = "Human readable string describing the error.", required = true)
    private String message;

    public ErrorResponse() {}

    /**
     * Builds new AuthError object.
     * @param code Unique identifier of the error.
     * @param message Human readable string describing the error.
     */
    public ErrorResponse(@NotBlank String code, @NotBlank String message) {
        this.code = code;
        this.message = message;
    }

}