package fr.uge.jee.reddit.post.comment;

import fr.uge.jee.reddit.post.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter
@Setter

@Entity
@Table(
    name = "comments",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"id", "post_id", "parent_id" }
    )
)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @OneToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne
    private Post parent;

    public Comment() {
    }

    public Comment(Post post, Post parent) {
        this.post = post;
        this.parent = parent;
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
