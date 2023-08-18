package com.example.videoVerseassignment.Service;

import com.example.videoVerseassignment.DTO.PostDto;
import com.example.videoVerseassignment.Entity.Post;

public interface PostService {

    public Post createPost(PostDto postDto) throws Exception;

    public Post updatePost(int id, String content, String postPhoto) throws Exception;

    public Post getPostById(int id) throws Exception;

    public String deletePost(int id) throws Exception;

}
