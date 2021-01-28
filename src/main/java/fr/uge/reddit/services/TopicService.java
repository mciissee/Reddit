package fr.uge.reddit.services;

import fr.uge.reddit.model.Topic;
import fr.uge.reddit.repository.TopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepo repository;

    public List<Topic> findAll() { return (List<Topic>) repository.findAll(); }

    public void save(Topic author) {
        repository.save(author);
    }

    public Optional<Topic> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Topic Topic) {
        repository.delete(Topic);
    }
}