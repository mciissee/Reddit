package fr.uge.jee.reddit.topic.post;

import fr.uge.jee.reddit.topic.comment.Comment;
import fr.uge.jee.reddit.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank
    @Size(max = 144)
    private String content;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @NotBlank
    @Column
    private Date date;

    @NotBlank
    @Column
    private int upvotes;

    @NotBlank
    @Column
    private int downvotes;

    @NotBlank
    @Column
    private int comments;

    public Post(@NotBlank @Size(max = 144) String content, @NotBlank User author, @NotBlank Date date, @NotBlank int upvotes, @NotBlank int downvotes) {
        this.content = content;
        this.author = author;
        this.date = date;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    public Post() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
