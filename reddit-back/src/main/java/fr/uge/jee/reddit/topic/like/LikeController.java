package fr.uge.jee.reddit.topic.like;

import fr.uge.jee.reddit.auth.AuthService;
import fr.uge.jee.reddit.user.User;
import fr.uge.jee.reddit.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class LikeController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;


    public Like createLike(){
        if(authService.currentUser().isEmpty())
            return null;
        return likeService.save(new Like(0, 0, 0, new ArrayList<>(), new ArrayList<>()));
    }

    /*public boolean upDownVote(boolean upDown){
        
    }*/
}
