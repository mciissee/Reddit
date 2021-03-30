package fr.uge.jee.reddit.topic.topic;

import fr.uge.jee.reddit.topic.comment.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
public class TopicFindByIdResponse {

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

    @Schema(description = "id of the comments of this topic", required = true)
    private List<Long> commentList;

    public TopicResponse(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.content = topic.getContent();
        this.author = topic.getAuthor().getUsername();
        this.upvote = topic.getLike().getUpvotes();
        this.downvote = topic.getLike().getDownvotes();
        this.hotness = topic.getLike().getHotness();
        this.date = topic.getDate();
        if(topic.getCommentList() == null)
            this.commentList = new ArrayList<>();
        else
            this.commentList = topic.getCommentList().stream().map(Comment::getId).collect(Collectors.toList());
    }

}
