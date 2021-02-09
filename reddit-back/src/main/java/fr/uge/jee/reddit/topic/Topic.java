package fr.uge.jee.reddit.topic;


import fr.uge.jee.reddit.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionException;

public class Topic {
    private User owner;
    private String topicName;
    private List<Comment> comments = new ArrayList<>();;

    public Topic(User owner,String topicName){
        this.owner = owner;
        this.topicName = topicName;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public void removeComment(Comment comment){
        comments.remove(comment);
    }
}
