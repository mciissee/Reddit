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

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    @Enumerated
    private VoteStatus voteStatus;

    public Vote() {
    }

    public Vote(Post post, User user, VoteStatus voteStatus) {
        this.post = post;
        this.user = user;
        this.voteStatus = voteStatus;
    }
}
