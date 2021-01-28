package fr.uge.reddit.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private User author;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "date")
    private Date date;
    @Column(name = "upvote")
    private int upvote;
    @Column(name = "downvote")
    private int downvote;
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Comment> commentList;

    public Topic(User author, String title, String content, Date date, int upvote, int downvote) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.date = date;
        this.upvote = upvote;
        this.downvote = downvote;
    }

    public Topic() {
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
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
                "author=" + author +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", upvote=" + upvote +
                ", downvote=" + downvote +
                ", commentList=" + commentList +
                '}';
    }
}
