package fr.uge.jee.reddit.topic.comment;

import fr.uge.jee.reddit.topic.like.Like;
import fr.uge.jee.reddit.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @ManyToOne
    private User owner;

    @Column
    @NotBlank
    @Size(max = 144)
    private String response;

    @OneToOne
    Like like;

    @Column
    private Date date;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> commentList;

    public Comment(@NotBlank User owner, @NotBlank @Size(max = 144) String response, Like like, Date date, List<Comment> commentList) {
        this.owner = owner;
        this.response = response;
        this.like = like;
        this.date = date;
        this.commentList = commentList;
    }

    public Comment() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public User getOwner() {
        return owner;
    }

    public Like getLike() {
        return like;
    }

    public void setLike(Like like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", owner=" + owner +
                ", response='" + response + '\'' +
                ", like=" + like +
                ", date=" + date +
                ", commentList=" + commentList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
