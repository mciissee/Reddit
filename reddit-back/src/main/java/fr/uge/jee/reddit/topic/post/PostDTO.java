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

    private VoteStatus userVoteStatus;

    private Date date;

    private int upvotes;

    private int downvotes;

    private int comments;

    public PostDTO(String content, String author, VoteStatus userVoteStatus, Date date, int upvotes, int downvotes, int comments) {
        this.content = content;
        this.author = author;
        this.userVoteStatus = userVoteStatus;
        this.date = date;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.comments = comments;
    }

    public PostDTO() {
    }
}
