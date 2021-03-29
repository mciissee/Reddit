package fr.uge.jee.reddit.topic.topic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class TopicCreateResponse {

    @NotBlank
    @Schema(description = "id of the created topic", required = true)
    private long id;

    public TopicCreateResponse(long id) {
        this.id = id;
    }

    public TopicCreateResponse() {
    }

}
