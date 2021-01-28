package fr.uge.jee.reddit.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable(name="userName") String userName) {
        var user = userService.findByUsername(userName);
        if (user.isPresent()) {
            return ResponseEntity.ok(userToDTO(user.get()));
        }
        return ResponseEntity.notFound().build();
    }

    private UserDTO userToDTO(User user) {
        var mapper = new ModelMapper();
        return mapper.map(user, UserDTO.class);
    }
}
