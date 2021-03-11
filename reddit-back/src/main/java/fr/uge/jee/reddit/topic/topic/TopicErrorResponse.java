package fr.uge.jee.reddit.topic.topic;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public class TopicErrorResponse {

    @NotBlank
    @Schema(description = "Unique identifier of the error.", required = true)
    private String code;

    @NotBlank
    @Schema(description = "Human readable string describing the error.", required = true)
    private String message;

    public TopicErrorResponse() {}

    /**
     * Builds new AuthError object.
     * @param code Unique identifier of the error.
     * @param message Human readable string describing the error.
     */
    public TopicErrorResponse(@NotBlank String code, @NotBlank String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}