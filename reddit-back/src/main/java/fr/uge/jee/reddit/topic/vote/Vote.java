package fr.uge.jee.reddit.topic.vote;

import fr.uge.jee.reddit.topic.post.Post;
import fr.uge.jee.reddit.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;

    public Vote() {
    }

    public Vote(Post post, User user) {
        this.post = post;
        this.user = user;
    }
}
