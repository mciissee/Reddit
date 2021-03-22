package fr.uge.jee.reddit.topic.like;

import fr.uge.jee.reddit.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    public Like save(Like like){return likeRepository.save(like);}

    public Boolean existsLikeByDownusersIsContaining(User user){ return likeRepository.existsLikeByDownusersIsContaining(user);}

    public Boolean existsLikeByUpusersIsContaining(User user){ return likeRepository.existsLikeByUpusersIsContaining(user);}
}
