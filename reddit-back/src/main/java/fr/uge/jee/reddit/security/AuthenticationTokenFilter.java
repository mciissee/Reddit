package fr.uge.jee.reddit.security;


import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *  Intercepts API requests to identify an user from the header of the requests
 *  by parsing a JWT token.
 *
 *  If there is no token provided and hence the user won't be authenticated.
 * 	It's Ok. Maybe the user accessing a public path or asking for a token.
 *
 * 	All secured paths that needs a token are already defined and secured in config class.
 * 	And If user tried to access without access token, then he won't be authenticated and an exception will be thrown.
 *
 */
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationTokenFilter.class);

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            // Try to extract jwt token from the request header
            var jwt = parse(request);
            if (jwt.isPresent()) { //
                // Load user details from the username present in the jwt token
                var userDetails = userDetailsService.loadUserByUsername(
                    jwtUtils.username(jwt.get())
                );

                // Create authentication object instantiating UsernamePasswordAuthenticationToken
                // which is using by spring to represent the current authenticated user.
                var authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
                );
                authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // authenticate the user
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Gets JWT token from the header of the given {@code request}.
     * @param request The request to parse.
     * @return A JWT token present in the header of {@code null}
     */
    private Optional<String> parse(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            var token = header.substring(7).trim();
            if (jwtUtils.validate(token)) {
                return Optional.of(token);
            }
        }
        return Optional.empty();
    }
}
