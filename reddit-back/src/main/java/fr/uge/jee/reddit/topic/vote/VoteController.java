package fr.uge.jee.reddit.topic.vote;

import fr.uge.jee.reddit.auth.AuthService;
import fr.uge.jee.reddit.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class VoteController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;


    public Vote createVote(){
        if(authService.currentUser().isEmpty())
            return null;

    }


}
