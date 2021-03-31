package fr.uge.jee.reddit.auth;

import fr.uge.jee.reddit.user.User;
import fr.uge.jee.reddit.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    public Optional<User> currentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails details = (UserDetails) auth.getPrincipal();
            String userName = details.getUsername();
            return userService.findByUsername(userName);
        }
        return Optional.empty();
    }

}
