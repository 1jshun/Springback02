package com.example.demo.likes;

import com.example.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true"
)

@RequestMapping("/likes")
@RestController
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;

    @GetMapping("/{feedIdx}")
    public ResponseEntity toggleLike(@PathVariable Long feedIdx) {
        User tempUser = User.builder().idx(1L).build();

        boolean isLiked = likesService.Like(feedIdx, tempUser);
        return ResponseEntity.ok(isLiked);
    }
}
