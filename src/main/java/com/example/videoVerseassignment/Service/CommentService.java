package com.example.videoVerseassignment.Service;

import com.example.videoVerseassignment.Entity.Comment;

public interface CommentService {

    public Comment addComment(String content, int postId) throws Exception;

    public Comment getCommentById(int id) throws Exception;

    public Comment updateComment(int id, String content) throws Exception;

    public void deleteComment(int id) throws Exception;
}
