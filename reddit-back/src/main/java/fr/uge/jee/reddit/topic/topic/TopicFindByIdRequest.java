package fr.uge.jee.reddit.topic.topic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
@Getter
@Setter
public class TopicFindByIdRequest {

    @Schema(description = "id of the topic.", required = true)
    private long id;

    public TopicFindByIdRequest() {
    }

}


