package com.example.videoVerseassignment.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String userName;

    private String firstName;

    private String lastName;

    private String password;

    private String address;

    private String mobileNo;

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

    @ManyToMany
    @JoinTable(
            name = "follow_users",
            joinColumns =@JoinColumn(name = "followed_id"),
            inverseJoinColumns =@JoinColumn(name = "follower_id")
    )
    private List<User>followers=new ArrayList<>();

    @ManyToMany(mappedBy = "followers")
    private List<User>ListOfFollowing=new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
