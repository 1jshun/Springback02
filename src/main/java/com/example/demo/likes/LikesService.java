package com.example.demo.likes;

import com.example.demo.feed.FeedRepository;
import com.example.demo.feed.model.Feed;
import com.example.demo.likes.model.Likes;
import com.example.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likesRepository;
    private final FeedRepository feedRepository;

    @Transactional
    public boolean Like(Long feedIdx, User user) {
        Feed feed = feedRepository.findById(feedIdx)
                .orElseThrow(() -> new RuntimeException("피드를 찾을 수 없습니다."));

        Optional<Likes> existingLike = likesRepository.findByUserAndFeed(user, feed);

        if (existingLike.isPresent()) {
            likesRepository.delete(existingLike.get());
            return false;
        } else {
            likesRepository.save(Likes.builder()
                    .user(user)
                    .feed(feed)
                    .build());
            return true;
        }
    }
}