package com.example.videoVerseassignment.Service;

import com.example.videoVerseassignment.DTO.SignUpDto;
import com.example.videoVerseassignment.DTO.UserUpdateDto;
import com.example.videoVerseassignment.Entity.User;

public interface UserService {

    public User signUp(SignUpDto signUpDto) throws Exception;

//    public User getByUserName(String userName) throws Exception;

    public User getAuthenticateUser() throws Exception;

    public User updateUser(int id, UserUpdateDto userUpdateDto) throws Exception;

    public User getUserById(int id) throws Exception;


    public void follow(int userId) throws Exception;
}
