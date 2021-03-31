package fr.uge.jee.reddit.topic.vote;

import fr.uge.jee.reddit.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;

    public Vote save(Vote vote){return voteRepository.saveAndFlush(vote);}

    public List<Vote> findAllByPost_id(long id){return voteRepository.findAllByPost_Id(id);}
}
