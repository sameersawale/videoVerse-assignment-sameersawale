package com.example.videoVerseassignment.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;


    private Date dateCreated;

    private Date dateUpdated;

    @OneToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn
    private Post post;

    @ManyToMany
    @JoinTable(
            name = "commentsOnPost",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userList=new ArrayList<>();

}
