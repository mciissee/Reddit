package fr.uge.reddit.repository;

import fr.uge.reddit.model.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepo extends CrudRepository<Topic, Long> {

}
