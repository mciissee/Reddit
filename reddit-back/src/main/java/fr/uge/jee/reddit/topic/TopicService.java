package fr.uge.jee.reddit.topic;

import fr.uge.jee.reddit.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public Topic save(Topic topic){return topicRepository.save(topic);}

    public List<Topic> findAllByAuthorOrderByHotness(User Author){return topicRepository.findAllByAuthorOrderByHotness(Author);}

    public List<Topic> findAllByContentContainingOrderByHotness(String content){return topicRepository.findAllByContentContainingOrderByHotness(content);}

    public List<Topic> findAllByDateIsLessThanEqualOrderByHotness(Date date){return topicRepository.findAllByDateIsLessThanEqualOrderByHotness(date);}

    public Optional<Topic> findById(long id){return topicRepository.findById(id);}
}
