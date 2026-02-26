package com.example.demo.feed.model;

import com.example.demo.likes.model.Likes;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String title;
    private String contents;


    @OneToMany(mappedBy = "feed", fetch = FetchType.LAZY)
    private List<Likes> likesList;
}
