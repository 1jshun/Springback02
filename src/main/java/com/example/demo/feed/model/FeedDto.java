package com.example.demo.feed.model;

import com.example.demo.user.model.User;
import lombok.*;

public class FeedDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FeedReq {
        private String title;
        private String contents;

        public Feed toEntity() {
            return Feed.builder()
                    .title(this.title)
                    .contents(this.contents)
                    .build();
        }
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Res{
        private Long idx;
        private String title;
        private String contents;

        public static Res from(Feed entity) {
            return Res.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .build();
        }
    }
}
