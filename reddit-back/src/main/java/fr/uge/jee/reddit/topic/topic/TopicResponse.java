package fr.uge.jee.reddit.topic.topic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
@Getter
@Setter
public class TopicResponse {

    @NotBlank
    @Schema(description = "id of the topic", required = true)
    private long id;

    @NotBlank
    @Schema(description = "title of the topic", required = true)
    private String title;

    @NotBlank
    @Schema(description = "content of the topic", required = true)
    private String content;

    @NotBlank
    @Schema(description = "author of the topic", required = true)
    private String author;

    @NotBlank
    @Schema(description = "upvote count", required = true)
    private int upvote;

    @NotBlank
    @Schema(description = "downvote count", required = true)
    private int downvote;

    @NotBlank
    @Schema(description = "date of creation of the topic", required = true)
    private Date date;

    @NotBlank
    @Schema(description = "hotness of the topic", required = true)
    private int hotness;

    @Schema(description = "count of comments of this topic", required = true)
    private int commentList;

    public TopicResponse(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.content = topic.getPost().getContent();
        this.author = topic.getPost().getAuthor().getUsername();
        this.upvote = topic.getPost().getUpvotes();
        this.downvote = topic.getPost().getDownvotes();
        this.hotness = this.upvote - this.downvote;
        this.date = topic.getPost().getDate();
        this.commentList = topic.getPost().getComments().size();
    }

}
