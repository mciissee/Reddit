package fr.uge.jee.reddit.topic.vote;

import fr.uge.jee.reddit.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;

    public Vote save(Vote vote){return voteRepository.saveAndFlush(vote);}

    public Boolean existsLikeByDownusersIsContaining(User user){ return voteRepository.existsLikeByDownusersIsContaining(user);}

    public Boolean existsLikeByUpusersIsContaining(User user){ return voteRepository.existsLikeByUpusersIsContaining(user);}
}
