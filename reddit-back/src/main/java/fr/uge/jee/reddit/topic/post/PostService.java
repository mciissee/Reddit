package fr.uge.jee.reddit.topic.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post save(Post post){return postRepository.saveAndFlush(post);}

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public Page<Post> findByAuthor_Username(Pageable page, String username) {
        return postRepository.findByAuthor_Username(page, username);
    }

    public Page<Post> findAllBy(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
