package com.example.videoVerseassignment.Service.Impl;

import com.example.videoVerseassignment.DTO.UserProfileDto;
import com.example.videoVerseassignment.Entity.User;
import com.example.videoVerseassignment.Entity.UserProfile;
import com.example.videoVerseassignment.Repository.UserProfileRepository;
import com.example.videoVerseassignment.Service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserServiceImpl userService;

    @Override
    public UserProfile addUserProfile(UserProfileDto userProfileDto) throws Exception {
        User user=userService.getAuthenticateUser();

        UserProfile userProfile=new UserProfile();
        userProfile.setBio(userProfileDto.getBio());
        userProfile.setProfilePhoto(userProfileDto.getProfilePhoto());
        userProfile.setFollowingCounts(0);
        userProfile.setFollowersCounts(0);
        userProfile.setUser(user);

        userProfileRepository.save(userProfile);

        return userProfile;
    }

    @Override
    public String updateUserProfile(int id, UserProfileDto userProfileDto) throws Exception {
        UserProfile userProfile=getById(id);

        if(userService.getAuthenticateUser().equals(userProfile.getUser())){
            userProfile.setProfilePhoto(userProfileDto.getProfilePhoto());
            userProfile.setBio(userProfileDto.getBio());
            userProfileRepository.save(userProfile);
            return "user profile updated successfully....";
        }
        else {
            throw new RuntimeException("please check user profile id");
        }
    }

    @Override
    public UserProfile getById(int id) {
        return userProfileRepository.findById(id).get();
    }
}
