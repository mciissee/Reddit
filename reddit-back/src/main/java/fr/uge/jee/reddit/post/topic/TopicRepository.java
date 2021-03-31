package fr.uge.jee.reddit.post.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{
    Optional<Topic> findByPostId(long postId);
    Page<Topic> findAllByOrderByPostHotnessDesc(Pageable pageable);
}
