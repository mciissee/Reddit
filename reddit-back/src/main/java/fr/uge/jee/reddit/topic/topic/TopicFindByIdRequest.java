package fr.uge.jee.reddit.topic.topic;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Size;

public class TopicFindByIdRequest {

    @Schema(description = "id of the topic.", required = true)
    private long id;

    public TopicFindByIdRequest() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}


