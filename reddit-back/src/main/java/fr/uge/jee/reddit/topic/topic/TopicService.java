package fr.uge.jee.reddit.topic.topic;

import fr.uge.jee.reddit.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public Topic save(Topic topic){return topicRepository.save(topic);}

    public List<Topic> findAllByAuthorOrderByLikeDesc(User Author){return topicRepository.findAllByAuthorOrderByLikeDesc(Author);}

    public List<Topic> findAllByOrderByLikeDesc(){return topicRepository.findAllByOrderByLikeDesc();}

    public Optional<Topic> findById(long id){return topicRepository.findById(id);}
}
