package com.example.demo.user.model;

import com.example.demo.likes.model.Likes;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.N;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String email;
    private String name;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Likes> likesList;
}
