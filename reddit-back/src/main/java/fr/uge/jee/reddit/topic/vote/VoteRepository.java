package fr.uge.jee.reddit.topic.vote;

import fr.uge.jee.reddit.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findAllByPost_Id(long id);
}
