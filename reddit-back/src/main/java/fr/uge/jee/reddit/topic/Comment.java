package fr.uge.jee.reddit.topic;

import fr.uge.jee.reddit.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

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

    @Column
    private int upVote;

    @Column
    private int downVote;

    @Column
    private Date date;

    @Column
    private int hotness;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> commentList;

    public Comment(long id, @NotBlank User owner, @NotBlank @Size(max = 144) String response, int upVote, int downVote, Date date, int hotness, List<Comment> commentList) {
        this.id = id;
        this.owner = owner;
        this.response = response;
        this.upVote = upVote;
        this.downVote = downVote;
        this.date = date;
        this.hotness = hotness;
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

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public int getUpVote() {
        return upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public String getResponse() {
        return response;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void incrementUpVote(){
        this.upVote += 1;
    }

    public void decrementUpVote(){
        this.upVote -= 1;
    }

    public void incrementDownVote(){
        this.downVote += 1;
    }

    public void decrementDownVote(){
        this.downVote -= 1;
    }

    public User getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", owner=" + owner +
                ", response='" + response + '\'' +
                ", upVote=" + upVote +
                ", downVote=" + downVote +
                ", date=" + date +
                ", hotness=" + hotness +
                ", commentList=" + commentList +
                '}';
    }
}
