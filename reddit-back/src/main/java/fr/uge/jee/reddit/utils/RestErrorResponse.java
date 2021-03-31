package fr.uge.jee.reddit.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotBlank;

/**
 * Representation of an error response of the rest api.
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
     * Builds new RestErrorResponse object.
     * @param code Unique identifier of the error.
     * @param message Human readable string describing the error.
     */
    public RestErrorResponse(@NotBlank String code, @NotBlank String message) {
        this.code = code;
        this.message = message;
    }


    public static ResponseEntity<RestErrorResponse> notFound(String message) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new RestErrorResponse("rest/not-found", message));
    }

    public static ResponseEntity<RestErrorResponse> badRequest(String message) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new RestErrorResponse("rest/bad-request", message));
    }

    public static ResponseEntity<RestErrorResponse> unauthorized(String message) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new RestErrorResponse("rest/unauthorized", message));
    }

}
