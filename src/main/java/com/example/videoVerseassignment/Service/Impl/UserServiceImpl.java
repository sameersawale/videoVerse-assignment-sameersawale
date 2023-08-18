package com.example.videoVerseassignment.Service.Impl;

import com.example.videoVerseassignment.DTO.SignUpDto;
import com.example.videoVerseassignment.DTO.UserUpdateDto;
import com.example.videoVerseassignment.Entity.User;
import com.example.videoVerseassignment.Repository.UserRepository;
import com.example.videoVerseassignment.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;
    @Override
    public User signUp(SignUpDto signUpDto) throws Exception {
        User user=new User();

        user.setUserName(signUpDto.getUserName());
        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setPassword(signUpDto.getPassword());
        user.setAddress(signUpDto.getAddress());
        user.setMobileNo(signUpDto.getMobileNo());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setBio(signUpDto.getBio());
        userRepository.save(user);

        return user;
    }


    @Override
    public User getAuthenticateUser() throws Exception {
        String userName= SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user= (User) userDetailsService.loadUserByUsername(userName);
        return user;
    }

    @Override
    public User updateUser(int id, UserUpdateDto userUpdateDto) throws Exception {
        User user=userRepository.findById(id).get();

        user.setFirstName(userUpdateDto.getFirstName());
        user.setLastName(userUpdateDto.getLastName());
        user.setAddress(userUpdateDto.getAddress());
        user.setMobileNo(userUpdateDto.getMobileNo());
        user.setBio(userUpdateDto.getBio());

        return userRepository.save(user);

    }

    @Override
    public User getUserById(int id) throws Exception {
        return userRepository.findById(id).get();
    }

    @Override
    public void follow(int userId) throws Exception {
        User authUser=getAuthenticateUser();

        if(authUser.getId()!=userId){
            User userToFollow=getUserById(userId);
            authUser.getListOfFollowing().add(userToFollow);
            authUser.setFollowingCounts(authUser.getFollowingCounts()+1);
            userToFollow.getFollowers().add(authUser);
            userToFollow.setFollowersCounts(userToFollow.getFollowersCounts()+1);

            userRepository.save(authUser);

            userRepository.save(userToFollow);
        }
        else {
            throw new InvalidDnDOperationException();
        }
    }


}
