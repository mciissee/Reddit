package fr.uge.jee.reddit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable(name="userName") String userName) {
        var maybeUser = userService.findByUsername(userName);
        if (maybeUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new UserDTO(maybeUser.get()));
    }
}
