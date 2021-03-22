package fr.uge.jee.reddit.topic.topic;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TopicCreateRequest {
    @Schema(description = "Title of the topic.", required = true)
    @Size(max = 144)
    private String Title;

    @Schema(description = "Content of the topic.", required = true)
    @Size(max = 144)
    private String Content;

    public TopicCreateRequest() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
