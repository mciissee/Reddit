package fr.uge.jee.reddit.topic.topic;

import fr.uge.jee.reddit.topic.comment.Comment;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopicFindByIdResponse {

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

    public TopicFindByIdResponse(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.content = topic.getContent();
        this.author = topic.getAuthor().getUsername();
        this.upvote = topic.getUpvote();
        this.downvote = topic.getDownvote();
        this.hotness = topic.getHotness();
        this.date = topic.getDate();
        if(topic.getCommentList() == null)
            this.commentList = new ArrayList<>();
        else
            this.commentList = topic.getCommentList().stream().map(Comment::getId).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHotness() {
        return hotness;
    }

    public void setHotness(int hotness) {
        this.hotness = hotness;
    }

    public List<Long> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Long> commentList) {
        this.commentList = commentList;
    }
}
