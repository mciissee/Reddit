package fr.uge.jee.reddit.topic.like;

import fr.uge.jee.reddit.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

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

    @OneToMany
    private List<User> upusers;

    @OneToMany
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public int getHotness() {
        return hotness;
    }

    public void setHotness(int hotness) {
        this.hotness = hotness;
    }

    public List<User> getUpusers() {
        return upusers;
    }

    public void setUpusers(List<User> upusers) {
        this.upusers = upusers;
    }

    public List<User> getDownusers() {
        return downusers;
    }

    public void setDownusers(List<User> downusers) {
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
