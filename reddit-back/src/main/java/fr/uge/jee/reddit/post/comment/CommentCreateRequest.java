package fr.uge.jee.reddit.post.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class CommentCreateRequest {

    @Schema(description = "content of the comment.", required = true)
    @Size(max = 144)
    private String content;

    public CommentCreateRequest() {
    }
}
