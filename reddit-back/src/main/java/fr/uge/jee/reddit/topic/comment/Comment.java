package fr.uge.jee.reddit.topic.comment;

import fr.uge.jee.reddit.topic.like.Like;
import fr.uge.jee.reddit.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import static javax.persistence.FetchType.LAZY;
@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @ManyToOne(fetch = LAZY)
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
            orphanRemoval = true,
            fetch = LAZY
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
