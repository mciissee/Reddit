package fr.uge.jee.reddit.post.topic;

import fr.uge.jee.reddit.post.Post;
import fr.uge.jee.reddit.post.PostService;
import fr.uge.jee.reddit.post.comment.CommentService;
import fr.uge.jee.reddit.post.vote.VoteService;
import fr.uge.jee.reddit.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private CommentService commentService;

    public Topic save(Topic topic){
        return topicRepository.saveAndFlush(topic);
    }

    @Transactional
    public Topic create(User author, String title, String content) {
        var post = postService.save(new Post(author, content));
        return save(new Topic(title, post));
    }

    @Transactional()
    public void delete(long id) {
        var topic = topicRepository.findByPostId(id).get();

        //delete comments
        var comments = commentService.findAllByParent(postService.findById(id).get());
        comments.forEach(comment -> commentService.delete(comment.getPost().getId()));

        //delete votes
        var votes = voteService.findAllOfPost(id);
        votes.forEach(vote -> voteService.delete(vote.getId()));

        //delete post
        postService.delete(topic.getPost());

        //delete comment
        topicRepository.delete(topic);
    }

    @Transactional(readOnly = true)
    public Optional<Topic> findById(long postId) {
        return topicRepository.findByPostId(postId);
    }

    @Transactional(readOnly = true)
    public Page<Topic> paginate(Pageable pageable) {
        return topicRepository.findAllByOrderByPostHotnessDesc(pageable);
    }
}
