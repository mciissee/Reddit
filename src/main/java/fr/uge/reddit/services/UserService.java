package fr.uge.reddit.services;

import fr.uge.reddit.model.User;
import fr.uge.reddit.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repository;

    public List<User> findAll() { return (List<User>) repository.findAll(); }

    public void save(User author) {
        repository.save(author);
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(User User) {
        repository.delete(User);
    }
}
