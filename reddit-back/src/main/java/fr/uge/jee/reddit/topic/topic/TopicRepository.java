package fr.uge.jee.reddit.topic.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{

    Optional<Topic> findById(long id);

    Page<Topic> findAllByOrderByPostDesc(Pageable pageable);
}
