package fr.uge.jee.reddit.topic.topic;

import fr.uge.jee.reddit.topic.comment.Comment;
import fr.uge.jee.reddit.topic.like.Like;
import fr.uge.jee.reddit.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;
@Getter
@Setter
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
    private Date date;

    @OneToOne
    @NotBlank
    private Like like;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> commentList;

    public Topic(@NotBlank @Size(max = 144) String title, @NotBlank @Size(max = 144) String content, @NotBlank User author, @NotBlank Date date, @NotBlank Like like, List<Comment> commentList) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
        this.like = like;
        this.commentList = commentList;
    }

    public Topic() {
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author=" + author +
                ", date=" + date +
                ", like=" + like +
                ", commentList=" + commentList +
                '}';
    }
}
