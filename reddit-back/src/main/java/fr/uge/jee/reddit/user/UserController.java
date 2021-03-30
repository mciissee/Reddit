package fr.uge.jee.reddit.user;

import fr.uge.jee.reddit.auth.AuthErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @Operation(summary = "change the password of a user.", tags = { "users" })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successful operation"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "author not found",
                    content = @Content(schema = @Schema(implementation = UserErrorResponse.class))
            )
    })
    @PostMapping(value = "/change-password", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> changePassword(@Valid @RequestBody UserCreateRequest request){
        var opt = userService.currentUser();
        if (opt.isPresent()) {
            User user = opt.get();
            if(!userService.checkIfValidOldPassword(user, request.getOldPassword())){
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(new AuthErrorResponse("auth/unauthorized","old password is not correct"));
            }
            userService.updatePassword(user, request.getNewPassword());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthErrorResponse("auth/unauthorized","user not connected"));
        }
    }

    private UserDTO userToDTO(User user) {
        var mapper = new ModelMapper();
        return mapper.map(user, UserDTO.class);
    }
}
