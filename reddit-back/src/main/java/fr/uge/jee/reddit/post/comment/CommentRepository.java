package fr.uge.jee.reddit.post.comment;

import fr.uge.jee.reddit.post.Post;
import fr.uge.jee.reddit.post.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByPostId(long postId);
    List<Comment> findByParentOrderByPostHotnessDesc(Post parent);
    List<Comment> findAllByPostAuthorUsernameOrderByPostHotnessDesc(String username);
}
