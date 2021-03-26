package fr.uge.jee.reddit.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private long id;
    private String username;
    private String email;
    private UserRole role;

    public UserDTO() {}
}
