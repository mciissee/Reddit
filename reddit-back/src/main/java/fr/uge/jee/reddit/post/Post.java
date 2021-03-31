package fr.uge.jee.reddit.post;

import fr.uge.jee.reddit.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @Column
    @NotBlank
    @Size(max = 144)
    private String content;

    @NotBlank
    @Column
    private Date date;

    @NotBlank
    @Column
    private int hotness;

    @NotBlank
    @Column
    private int upvotes;

    @NotBlank
    @Column
    private int downvotes;

    @NotBlank
    @Column
    private int comments;

    public Post(User author, String content) {
        this.content = content;
        this.author = author;
        this.date = new Date(System.currentTimeMillis());
        this.hotness = 0;
        this.upvotes = 0;
        this.downvotes = 0;
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
