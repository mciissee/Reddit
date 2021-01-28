package fr.uge.reddit.model;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    private boolean isAuthentified;

    public User() {
    }

    public User(int id, String username, boolean isAuthentified) {
        this.id = id;
        this.username = username;
        this.isAuthentified = isAuthentified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAuthentified() {
        return isAuthentified;
    }

    public void setAuthentified(boolean authentified) {
        isAuthentified = authentified;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", isAuthentified=" + isAuthentified +
                '}';
    }
}
