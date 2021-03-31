package fr.uge.jee.reddit.post.topic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class TopicCreateRequest {
    @Schema(description = "title of the topic.", required = true)
    @Size(max = 144)
    private String title;

    @Schema(description = "content of the topic.", required = true)
    @Size(max = 144)
    private String content;

    public TopicCreateRequest() {
    }
}
