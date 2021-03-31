package fr.uge.jee.reddit.topic.post;

import fr.uge.jee.reddit.topic.vote.VoteStatus;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class PostDTO {

    private String content;

    private String author;

    private Date date;

    private int upvotes;

    private int downvotes;

    private int comments;

    public PostDTO(Post post) {
        this.content = post.getContent();
        this.author = post.getAuthor().getUsername();
        this.date = post.getDate();
        this.upvotes = post.getUpvotes();
        this.downvotes = post.getDownvotes();
        this.comments = post.getComments();
    }

    public PostDTO() {
    }

}
