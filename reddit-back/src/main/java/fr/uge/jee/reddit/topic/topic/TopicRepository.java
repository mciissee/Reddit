package fr.uge.jee.reddit.topic.topic;

import fr.uge.jee.reddit.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{

    ResponseEntity<?> findAllByOrderByLikeDesc(Pageable pageable);

    ResponseEntity<?> findAllByAuthorOrderByLikeDesc(User author);

    Optional<Topic> findById(long id);

}
