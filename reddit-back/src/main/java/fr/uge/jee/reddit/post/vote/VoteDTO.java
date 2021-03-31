package fr.uge.jee.reddit.post.vote;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class VoteDTO {
    @NotBlank
    @Schema(description = "id of the vote's post", required = true)
    private long postId;
    @NotBlank
    @Schema(description = "id of the vote's author", required = true)
    private String username;

    @NotBlank
    @Schema(description = "type of the vote", required = true)
    private VoteTypes type;

    public VoteDTO() {}

    public VoteDTO(Vote vote) {
        this.postId = vote.getPost().getId();
        this.username = vote.getUser().getUsername();
        this.type = vote.getType();
    }
}
