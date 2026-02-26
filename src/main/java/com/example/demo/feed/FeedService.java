package com.example.demo.feed;

import com.example.demo.feed.model.Feed;
import com.example.demo.feed.model.FeedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FeedService {
    private final FeedRepository feedRepository;

    public List<FeedDto.Res> list() {
        List<Feed> feedList = feedRepository.findAll();
        return feedList.stream().map(FeedDto.Res::from).toList();
    }
}
