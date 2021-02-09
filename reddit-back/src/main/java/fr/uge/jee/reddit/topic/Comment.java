package fr.uge.jee.reddit.topic;

import fr.uge.jee.reddit.user.User;

public class Comment {
    private User owner;
    private String response;
    private int upVote;
    private int downVote;

    public Comment(User owner,String response){
        this.owner = owner;
        this.response = response;
        this.upVote = 0;
        this.downVote = 0;
    }

    public Comment(String response, int upVote, int downVote){
        this.response = response;
        this.upVote = upVote;
        this.downVote = downVote;
    }

    public int getUpVote() {
        return upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public String getResponse() {
        return response;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void incrementUpVote(){
        this.upVote += 1;
    }

    public void decrementUpVote(){
        this.upVote -= 1;
    }

    public void incrementDownVote(){
        this.downVote += 1;
    }

    public void decrementDownVote(){
        this.downVote -= 1;
    }

    public User getOwner() {
        return owner;
    }
}
