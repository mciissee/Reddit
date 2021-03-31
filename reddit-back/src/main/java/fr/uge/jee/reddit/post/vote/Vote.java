package fr.uge.jee.reddit.post.vote;

import fr.uge.jee.reddit.post.Post;
import fr.uge.jee.reddit.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "votes")
// TODO add unique constraint to columns post_id + user_id
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
    private VoteTypes type;

    public Vote() {}

    public Vote(Post post, User user, VoteTypes type) {
        this.post = post;
        this.user = user;
        this.type = type;
    }
}
