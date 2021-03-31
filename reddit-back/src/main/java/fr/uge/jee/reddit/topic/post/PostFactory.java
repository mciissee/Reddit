package fr.uge.jee.reddit.topic.post;

import fr.uge.jee.reddit.user.User;

import java.sql.Date;
import java.util.ArrayList;

public class PostFactory {

    public static Post createPost(String content, User user) {
        return new Post(content,  user, new Date(System.currentTimeMillis()), 0, 0, new ArrayList<>());
    }
}
