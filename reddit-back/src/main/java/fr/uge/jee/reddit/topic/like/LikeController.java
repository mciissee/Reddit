package fr.uge.jee.reddit.topic.like;

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
    private UserService userService;

    private LikeService likeService;

    private Optional<User> currentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails details = (UserDetails) auth.getPrincipal();
            String userName = details.getUsername();
            return userService.findByUsername(userName);
        }
        return Optional.empty();
    }

    public Like createLike(){
        if(currentUser().isEmpty())
            return null;
        return likeService.save(new Like(0, 0, 0, new ArrayList<>(), new ArrayList<>()));
    }

    /*public boolean upDownVote(boolean upDown){
        
    }*/
}
