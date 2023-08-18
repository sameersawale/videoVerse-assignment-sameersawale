package com.example.videoVerseassignment.Controller;

import com.example.videoVerseassignment.Entity.Comment;
import com.example.videoVerseassignment.Service.Impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;

    @PostMapping("/add")
    public  String addComment(@RequestParam("postId") int postId, @RequestParam("content") String content) throws Exception {
        commentService.addComment(content, postId);

        return "comment on post added successfully....";
    }

    @GetMapping("/get/{id}")
    public Comment getCommentById(@PathVariable("id") int id) throws Exception {
        return commentService.getCommentById(id);
    }

    @PutMapping("update/{id}")
    public String updateComment(@PathVariable("id") int id, @RequestParam("content") String content) throws Exception {
         commentService.updateComment(id, content);

         return "comment updated successfully.....";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteComment(@PathVariable("id") int id) throws Exception {
        commentService.deleteComment(id);

        return "Comment deleted successfully....";
    }
}
