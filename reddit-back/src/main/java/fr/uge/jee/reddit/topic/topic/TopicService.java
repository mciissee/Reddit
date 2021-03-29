package fr.uge.jee.reddit.topic.topic;

import fr.uge.jee.reddit.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public Topic save(Topic topic){
        return topicRepository.saveAndFlush(topic);
    }

    public ResponseEntity<?> findAllByAuthorOrderByLikeDesc(User Author){return ResponseEntity.ok(topicRepository.findAllByAuthorOrderByLikeDesc(Author));}

    public Page<Topic> findAllByOrderByLikeDesc(Pageable pageable){return topicRepository.findAllByOrderByLikeDesc(pageable);}

    public Optional<Topic> findById(long id){return topicRepository.findById(id);}
}
