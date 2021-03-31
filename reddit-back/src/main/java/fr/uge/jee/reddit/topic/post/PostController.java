package fr.uge.jee.reddit.topic.post;

import fr.uge.jee.reddit.topic.comment.CommentService;
import fr.uge.jee.reddit.topic.topic.TopicService;
import fr.uge.jee.reddit.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/posts")
@Tag(name = "posts", description = "post API.")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;



}
