package fr.uge.jee.reddit.topic.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment save(Comment comment){return commentRepository.save(comment);}

}
