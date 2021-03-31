package fr.uge.jee.reddit.topic.comment;

import fr.uge.jee.reddit.topic.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByParent(Post parent);
}
