package fr.uge.jee.reddit.post.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommentDTO {
    @NotBlank
    @Schema(description = "id of the comment post", required = true)
    private long id;

    @NotBlank
    @Schema(description = "content of the comment", required = true)
    private String content;

    @NotBlank
    @Schema(description = "author of the comment", required = true)
    private String author;

    @NotBlank
    @Schema(description = "upvotes count", required = true)
    private int upvotes;

    @NotBlank
    @Schema(description = "downvotes count", required = true)
    private int downvotes;

    @NotBlank
    @Schema(description = "date of creation of the comment", required = true)
    private long date;

    @NotBlank
    @Schema(description = "hotness of the comment", required = true)
    private int hotness;

    @Schema(description = "number of comments of this comment", required = true)
    private int comments;

    public CommentDTO() {}

    public CommentDTO(Comment topic) {
        this.id = topic.getPost().getId();
        this.date = topic.getPost().getDate().getTime();
        this.content = topic.getPost().getContent();
        this.author = topic.getPost().getAuthor().getUsername();
        this.hotness = topic.getPost().getHotness();
        this.upvotes = topic.getPost().getUpvotes();
        this.downvotes = topic.getPost().getDownvotes();
        this.comments = topic.getPost().getComments();
    }
}
