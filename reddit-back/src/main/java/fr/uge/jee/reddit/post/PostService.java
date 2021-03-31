package fr.uge.jee.reddit.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post save(Post post) { return postRepository.saveAndFlush(post); }

    public void delete(Post post) { postRepository.delete(post); }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }
}
