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
        this.content = comment.getResponse();
        this.author = comment.getOwner().getUsername();
        this.upvote = comment.getLike().getUpvotes();
        this.downvote = comment.getLike().getDownvotes();
        this.hotness = comment.getLike().getHotness();
        this.date = comment.getDate();
        this.subComments = comment.getCommentList().stream().map(Comment::getId).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getUpvote() {
        return upvote;
    }

    public void setUpvote(int upvote) {
        this.upvote = upvote;
    }

    public int getDownvote() {
        return downvote;
    }

    public void setDownvote(int downvote) {
        this.downvote = downvote;
    }

    public int getHotness() {
        return hotness;
    }

    public void setHotness(int hotness) {
        this.hotness = hotness;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Long> getSubComments() {
        return subComments;
    }

    public void setSubComments(List<Long> subComments) {
        this.subComments = subComments;
    }
}
