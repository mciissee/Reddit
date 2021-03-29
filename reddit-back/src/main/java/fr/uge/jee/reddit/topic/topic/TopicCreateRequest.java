package fr.uge.jee.reddit.topic.topic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Getter
@Setter
public class TopicCreateRequest {
    @Schema(description = "Title of the topic.", required = true)
    @Size(max = 144)
    private String Title;

    @Schema(description = "Content of the topic.", required = true)
    @Size(max = 144)
    private String Content;

    public TopicCreateRequest() {
    }

}
