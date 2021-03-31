package fr.uge.jee.reddit.topic.comment;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CommentResponse {

    @NotBlank
    @Schema(description = "id of the comment")
    private long id;

    @NotBlank
    @Schema(description = "content of the comment")
    private String content;

    @NotBlank
    @Schema(description = "author of the comment")
    private String author;

    @NotBlank
    @Schema(description = "count of upvote")
    private int upvote;

    @NotBlank
    @Schema(description = "count of downvote")
    private int downvote;

    @NotBlank
    @Schema(description = "hotness")
    private int hotness;

    @NotBlank
    @Schema(description = "date")
    private Date date;

    @NotBlank
    @Schema(description = "list of the id of the sub comments")
    private List<Long> subComments;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
