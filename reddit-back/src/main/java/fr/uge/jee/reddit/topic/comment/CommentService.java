package fr.uge.jee.reddit.topic.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment save(Comment comment){return commentRepository.save(comment);}

    public Optional<Comment> findById(long id){return  commentRepository.findById(id);}
}
