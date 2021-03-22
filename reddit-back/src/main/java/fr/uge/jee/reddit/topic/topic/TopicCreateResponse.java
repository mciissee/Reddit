package fr.uge.jee.reddit.topic.topic;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public class TopicCreateResponse {

    @NotBlank
    @Schema(description = "id of the created topic", required = true)
    private long id;

    public TopicCreateResponse(long id) {
    }

    public TopicCreateResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
