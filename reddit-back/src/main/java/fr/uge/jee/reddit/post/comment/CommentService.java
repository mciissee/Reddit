package fr.uge.jee.reddit.post.comment;

import fr.uge.jee.reddit.post.Post;
import fr.uge.jee.reddit.post.PostService;
import fr.uge.jee.reddit.post.vote.VoteService;
import fr.uge.jee.reddit.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private VoteService voteService;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional()
    public void delete(long postId) {
        var comment = commentRepository.findByPostId(postId).get();

        // decrement parent comment counter
        var parent = postService.findById(comment.getParent().getId()).get();
        parent.setComments(parent.getComments() - 1);
        postService.save(parent);

        //delete votes
        var votes = voteService.findAllOfPost(postId);
        votes.forEach(vote -> voteService.delete(vote.getId()));

        // delete post
        postService.delete(comment.getPost());

        // delete comment
        commentRepository.delete(comment);
    }

    @Transactional()
    public Comment create(User author, Post parent, String content) {
        // increment parent comments counter
        parent.setComments(parent.getComments() + 1);
        postService.save(parent);

        // create comment post
        var commentPost = new Post(author, content);
        postService.save(commentPost);

        // save comment
        var comment = new Comment(commentPost, parent);
        return save(comment);
    }

    @Transactional(readOnly = true)
    public List<Comment> findAllByParent(Post parent) {
        return commentRepository.findByParentOrderByPostHotnessDesc(parent);
    }

    @Transactional(readOnly = true)
    public List<Comment> findAllByParentOfAuthor(String username) {
        return commentRepository.findAllByPostAuthorUsernameOrderByPostHotnessDesc(username);
    }
}
