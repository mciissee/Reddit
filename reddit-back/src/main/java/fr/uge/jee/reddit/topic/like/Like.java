package fr.uge.jee.reddit.topic.like;

import fr.uge.jee.reddit.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
@Getter
@Setter
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank
    private int upvotes;

    @Column
    @NotBlank
    private int downvotes;

    @Column
    @NotBlank
    @OrderBy
    private int hotness;

    @OneToMany(fetch = LAZY)
    private List<User> upusers;

    @OneToMany(fetch = LAZY)
    private List<User> downusers;

    public Like() {
    }

    public Like(@NotBlank int upvotes, @NotBlank int downvotes, @NotBlank int hotness, List<User> upusers, List<User> downusers) {
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.hotness = hotness;
        this.upusers = upusers;
        this.downusers = downusers;
    }

    public boolean removeLike(User user){
        return upusers.remove(user);
    }

    public boolean addLike(User user){
        if(!upusers.contains(user)) {
            downusers.remove(user);
            upusers.add(user);
            return true;
        }
        return false;
    }

    public boolean removeDislike(User user){
        return downusers.remove(user);
    }

    public boolean addDislike(User user){
        if(!downusers.contains(user)) {
            upusers.remove(user);
            downusers.add(user);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                ", hotness=" + hotness +
                ", upusers=" + upusers +
                ", downusers=" + downusers +
                '}';
    }
}
