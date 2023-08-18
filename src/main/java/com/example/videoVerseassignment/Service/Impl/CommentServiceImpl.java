package com.example.videoVerseassignment.Service.Impl;

import com.example.videoVerseassignment.Entity.Comment;
import com.example.videoVerseassignment.Entity.Post;
import com.example.videoVerseassignment.Entity.User;
import com.example.videoVerseassignment.Repository.CommentRepository;
import com.example.videoVerseassignment.Repository.PostRepository;
import com.example.videoVerseassignment.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostServiceImpl postService;

    @Autowired
    UserServiceImpl userService;

    @Override
    public Comment addComment(String content, int postId) throws Exception {
        Post post=postService.getPostById(postId);

        User authUser=userService.getAuthenticateUser();

        List<User> followedList=authUser.getListOfFollowing();

        for(User followedUser:followedList){
            if(followedUser.equals(post.getUser())){
                Comment comment=new Comment();
                comment.setComment(content);
                comment.setUser(authUser);
                comment.setPost(post);
                comment.setDateCreated(new Date());
                comment.setDateUpdated(new Date());

                return commentRepository.save(comment);
            }
        }
        throw new InvalidDnDOperationException();
    }

    @Override
    public Comment getCommentById(int id) throws Exception {
        return commentRepository.findById(id).get();
    }

    @Override
    public Comment updateComment(int id, String content) throws Exception {
        User user=userService.getAuthenticateUser();
        Comment comment=getCommentById(id);
        if(comment.getUser().equals(user)){
            comment.setComment(content);
            comment.setDateUpdated(new Date());
            return commentRepository.save(comment);
        }else {
            throw new InvalidDnDOperationException();
        }
    }

    @Override
    public void deleteComment(int id) throws Exception {
        User authUser=userService.getAuthenticateUser();

        Comment comment=getCommentById(id);

        if(comment.getUser().equals(authUser)){
            commentRepository.delete(comment);
        }
        else{
            throw new InvalidDnDOperationException();
        }

    }


}
