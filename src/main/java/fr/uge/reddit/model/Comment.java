package fr.uge.reddit.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private User author;
    @Column(name = "content")
    private String content;
    @Column(name = "date")
    private Date date;
    @Column(name = "upvote")
    private int upvote;
    @Column(name = "downvote")
    private int downvote;

    public Comment(int id, User author, String content, Date date, int upvote, int downvote) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.date = date;
        this.upvote = upvote;
        this.downvote = downvote;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        return "Comment{" +
                "id=" + id +
                ", author=" + author +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", upvote=" + upvote +
                ", downvote=" + downvote +
                '}';
    }
}
