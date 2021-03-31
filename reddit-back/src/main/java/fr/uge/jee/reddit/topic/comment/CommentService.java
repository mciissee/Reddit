package fr.uge.jee.reddit.topic.comment;

import fr.uge.jee.reddit.topic.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment save(Comment comment){return commentRepository.save(comment);}

    public List<Comment> findAllByParent(Post parent){return commentRepository.findAllByParent(parent);}
}
