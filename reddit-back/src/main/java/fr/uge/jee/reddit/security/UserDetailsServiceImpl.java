package fr.uge.jee.reddit.security;

import fr.uge.jee.reddit.user.User;
import fr.uge.jee.reddit.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Provides methods to load users information's.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByUsername(userName).orElseThrow(
            () -> new UsernameNotFoundException("User Not Found with username: " + userName)
        );
        var roles = new HashSet<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));
        return buildUserForAuthentication(user, new ArrayList<>(roles));
    }

    private UserDetails buildUserForAuthentication(
        User user,
        List<GrantedAuthority> authorities
    ) {
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            true,
            true,
            true,
            true,
            authorities
        );
    }
}
