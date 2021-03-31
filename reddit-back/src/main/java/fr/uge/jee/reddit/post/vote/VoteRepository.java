package fr.uge.jee.reddit.post.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByUserUsernameAndPostId(String username, long postId);
    List<Vote> findAllByPostId(long postId);
}
