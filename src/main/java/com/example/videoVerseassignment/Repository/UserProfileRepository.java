package com.example.videoVerseassignment.Repository;

import com.example.videoVerseassignment.Entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository  extends JpaRepository<UserProfile, Integer> {
}