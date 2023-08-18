package com.example.videoVerseassignment.Service.Impl;

import com.example.videoVerseassignment.DTO.PostDto;
import com.example.videoVerseassignment.Entity.Post;
import com.example.videoVerseassignment.Entity.User;
import com.example.videoVerseassignment.Repository.PostRepository;
import com.example.videoVerseassignment.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Date;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserServiceImpl userService;

    @Override
    public Post createPost(PostDto postDto) throws Exception {

        User user=userService.getAuthenticateUser();
        Post post=new Post();

        post.setPostPhoto(postDto.getPostPhoto());

        post.setContent(post.getContent());

        post.setUser(user);

        post.setDateCreated(new Date());

        post.setDateLastModified(new Date());

        postRepository.save(post);

        return post;
    }

    @Override
    public Post updatePost(int id, String content,String updatePostPhoto) throws Exception {
        Post post=postRepository.findById(id).get();
        post.setPostPhoto(updatePostPhoto);
        post.setContent(content);
        post.setDateLastModified(new Date());

        postRepository.save(post);

        return post;
    }

    @Override
    public Post getPostById(int id) throws Exception {
        return postRepository.findById(id).get();
    }

    @Override
    public String deletePost(int id) throws Exception {
        User authUser=userService.getAuthenticateUser();

        Post post=getPostById(id);

        if(authUser.equals(post.getUser())){
            postRepository.delete(post);

            return "post deleted successfully....";
        }
        else {
            throw new InvalidDnDOperationException();
        }
    }
}
