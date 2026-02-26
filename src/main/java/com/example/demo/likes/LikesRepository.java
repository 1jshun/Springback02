package com.example.demo.likes;

import com.example.demo.feed.model.Feed;
import com.example.demo.likes.model.Likes;
import com.example.demo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByUserAndFeed(User user, Feed feed);
}
