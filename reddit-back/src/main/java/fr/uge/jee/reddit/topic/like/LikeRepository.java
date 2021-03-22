package fr.uge.jee.reddit.topic.like;

import fr.uge.jee.reddit.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Boolean existsLikeByDownusersIsContaining(User user);

    Boolean existsLikeByUpusersIsContaining(User user);
}
