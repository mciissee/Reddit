package fr.uge.jee.reddit.topic.topic;

import fr.uge.jee.reddit.topic.comment.Comment;
import fr.uge.jee.reddit.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank
    @Size(max = 144)
    private String title;

    @Column
    @NotBlank
    @Size(max = 144)
    private String content;

    @NotBlank
    @ManyToOne
    private User author;

    @NotBlank
    @Column
    private int upvote;

    @NotBlank
    @Column
    private int downvote;

    @NotBlank
    @Column
    private Date date;

    @NotBlank
    @Column
    private int hotness;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> commentList;

    public Topic(@NotBlank @Size(max = 144) String title, @NotBlank @Size(max = 144) String content, @NotBlank User author, int upvote, int downvote, int hotness, Date date, List<Comment> commentList) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.upvote = upvote;
        this.downvote = downvote;
        this.date = date;
        this.hotness = hotness;
        this.commentList = commentList;
    }

    public Topic() {
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
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

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author=" + author +
                ", upvote=" + upvote +
                ", downvote=" + downvote +
                ", date=" + date +
                ", hotness=" + hotness +
                ", commentList=" + commentList +
                '}';
    }
}
