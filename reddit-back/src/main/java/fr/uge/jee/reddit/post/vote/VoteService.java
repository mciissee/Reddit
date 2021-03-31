package fr.uge.jee.reddit.post.vote;

import fr.uge.jee.reddit.post.Post;
import fr.uge.jee.reddit.post.PostService;
import fr.uge.jee.reddit.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PostService postService;

    public Vote save(Vote vote) { return voteRepository.saveAndFlush(vote); }

    @Transactional(readOnly = true)
    public Optional<Vote> find(User user, Post post) {
        return voteRepository.findByUserUsernameAndPostId(
            user.getUsername(),
            post.getId()
        );
    }

    @Transactional
    public void delete(long id){
        voteRepository.delete(voteRepository.findById(id).get());
    }

    @Transactional
    public void unvote(User user, Post post) {
        Vote vote;
        Optional<Vote> maybeVote = voteRepository.findByUserUsernameAndPostId(
            user.getUsername(),
            post.getId()
        );

        if (maybeVote.isPresent()) {
            vote = maybeVote.get();
            if (VoteTypes.DOWN_VOTE == vote.getType()) {
                post.setDownvotes(post.getDownvotes() - 1);
            } else {
                post.setUpvotes(post.getUpvotes() - 1);
            }

            voteRepository.delete(vote);

            post.setHotness(post.getUpvotes() - post.getDownvotes());
            postService.save(post);
        }
    }

    @Transactional
    public Vote upvote(User user, Post post) {
        Vote vote;
        Optional<Vote> maybeVote = voteRepository.findByUserUsernameAndPostId(
            user.getUsername(),
            post.getId()
        );
        if (maybeVote.isEmpty()) {
            vote = save(new Vote(post, user, VoteTypes.UP_VOTE));
            post.setUpvotes(post.getUpvotes() + 1);
        } else {
            vote = maybeVote.get();
            if (VoteTypes.DOWN_VOTE == vote.getType()) {
                post.setUpvotes(post.getUpvotes() + 1);
                post.setDownvotes(post.getDownvotes() - 1);
                vote.setType(VoteTypes.UP_VOTE);
                save(vote);
            }
        }

        post.setHotness(post.getUpvotes() - post.getDownvotes());
        postService.save(post);

        return vote;
    }

    @Transactional
    public Vote downvote(User user, Post post) {
        Vote vote;
        Optional<Vote> maybeVote = voteRepository.findByUserUsernameAndPostId(
            user.getUsername(),
            post.getId()
        );
        if (maybeVote.isEmpty()) {
            vote = save(new Vote(post, user, VoteTypes.DOWN_VOTE));
            post.setDownvotes(post.getDownvotes() + 1);
        } else {
            vote = maybeVote.get();
            if (VoteTypes.UP_VOTE == vote.getType()) {
                post.setDownvotes(post.getDownvotes() + 1);
                post.setUpvotes(post.getUpvotes() - 1);
                vote.setType(VoteTypes.DOWN_VOTE);
                save(vote);
            }
        }

        post.setHotness(post.getUpvotes() - post.getDownvotes());
        postService.save(post);

        return vote;
    }

    @Transactional(readOnly = true)
    public List<Vote> findAllOfPost(long postId){
        return voteRepository.findAllByPostId(postId);
    }
}
