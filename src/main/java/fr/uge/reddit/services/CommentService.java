package fr.uge.reddit.services;

import fr.uge.reddit.model.Comment;
import fr.uge.reddit.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepo repository;

    public List<Comment> findAll() { return (List<Comment>) repository.findAll(); }

    public void save(Comment author) {
        repository.save(author);
    }

    public Optional<Comment> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Comment Comment) {
        repository.delete(Comment);
    }
}