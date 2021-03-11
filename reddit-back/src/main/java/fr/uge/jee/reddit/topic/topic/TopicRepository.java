package fr.uge.jee.reddit.topic.topic;

import fr.uge.jee.reddit.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findAllByAuthorOrderByHotness(User Author);

    List<Topic> findAllByContentContainingOrderByHotness(String content);

    List<Topic> findAllByDateIsLessThanEqualOrderByHotness(Date date);

    Optional<Topic> findById(long id);

}
