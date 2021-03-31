package fr.uge.jee.reddit.topic.comment;

import fr.uge.jee.reddit.topic.post.PostDTO;

public class CommentDTO {
    private long id;

    private PostDTO post;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.post = new PostDTO(comment.getPost());
    }
}
