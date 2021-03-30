package fr.uge.jee.reddit.post.comment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.uge.jee.reddit.post.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByPostId(long postId);
    List<Comment> findByParentOrderByPostHotnessDesc(Post parent);
    List<Comment> findAllByPostAuthorUsernameOrderByPostHotnessDesc(String username);
}
