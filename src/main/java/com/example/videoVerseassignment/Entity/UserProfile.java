package com.example.videoVerseassignment.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "userProfile")
@Getter
@Setter
@Data
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bio;

    @Column(nullable = true, length = 64)
    private String profilePhoto;

    private int followersCounts;

    private int followingCounts;

    @Transient
    public String getProfilePhotoPath(){
        if(profilePhoto==null || id==0)
            return null;
        return"/profilePhoto/"+id+"/"+profilePhoto;
    }


    @OneToOne
    @JoinColumn
    private User user;
}
