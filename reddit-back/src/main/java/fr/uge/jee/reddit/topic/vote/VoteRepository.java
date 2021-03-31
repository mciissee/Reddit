package fr.uge.jee.reddit.topic.vote;

import fr.uge.jee.reddit.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Boolean existsLikeByDownusersIsContaining(User user);

    Boolean existsLikeByUpusersIsContaining(User user);
}
