package com.example.demo.feed;


import com.example.demo.feed.model.FeedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true"
)

@RequestMapping("/feed")
@RestController
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;

    @GetMapping("/list")
    public ResponseEntity list() {
        List<FeedDto.Res> dto = feedService.list();

        Map<String, Object> response = new HashMap<>();
        response.put("isSuccess", true);
        response.put("result", dto);
        return ResponseEntity.ok(response);
    }
}
