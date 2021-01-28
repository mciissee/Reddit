package fr.uge.jee.reddit;

import fr.uge.jee.reddit.user.User;
import fr.uge.jee.reddit.user.UserRole;
import fr.uge.jee.reddit.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RedditApplicationRunner implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${reddit.app.adminEmail}")
    private String adminEmail;
    @Value("${reddit.app.adminUserName}")
    private String adminUserName;
    @Value("${reddit.app.adminPassword}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {
        var admin = userService.findByUsername(adminUserName);
        if (admin.isEmpty()) {
            userService.save(
                new User(adminUserName, adminEmail, passwordEncoder.encode(adminPassword), UserRole.ADMIN)
            );
        }
    }
}
