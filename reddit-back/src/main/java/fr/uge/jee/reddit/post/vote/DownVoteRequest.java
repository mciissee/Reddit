package fr.uge.jee.reddit.post.vote;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DownVoteRequest {
    private long postId;
    private String username;
    public DownVoteRequest() {}
}
