package com.example.videoVerseassignment.Service;

import com.example.videoVerseassignment.DTO.UserProfileDto;
import com.example.videoVerseassignment.Entity.UserProfile;

public interface UserProfileService {

    public UserProfile addUserProfile(UserProfileDto userProfileDto) throws Exception;

    public String updateUserProfile(int id, UserProfileDto userProfileDto) throws Exception;

    public UserProfile getById(int id);
}
