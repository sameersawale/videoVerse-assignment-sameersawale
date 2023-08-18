package com.example.videoVerseassignment.Controller;

import com.example.videoVerseassignment.DTO.PostDto;
import com.example.videoVerseassignment.Entity.Post;
import com.example.videoVerseassignment.Service.Impl.PostServiceImpl;
import com.example.videoVerseassignment.fileUpload.FileUploadUtil;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.PublicKey;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public String createPost(@RequestParam("file") MultipartFile multipartFile, @RequestPart("post")PostDto postDto) throws Exception {
        String fileName=multipartFile.getOriginalFilename();
        postDto.setPostPhoto(fileName);
        Post post=postService.createPost(postDto);

        String uploadDir="./postPhoto/"+post.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "post created successfully";
    }

    @PutMapping(value = "/update/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String updatePost(@PathVariable("id") int id, @RequestParam("file") MultipartFile multipartFile, @RequestParam("update") String content) throws Exception {
        Post post=postService.getPostById(id);

        String oldFileName=post.getPostPhoto();

        String newFileName=multipartFile.getOriginalFilename();

        post.setPostPhoto(newFileName);

        postService.updatePost(id, newFileName, content);

        String uploadDir="./postPhoto/"+post.getId();

        FileUploadUtil.updateFile(uploadDir, oldFileName, newFileName, multipartFile);

        return "post updated successfully...";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") int id) throws Exception {
        Post post=postService.getPostById(id);

        String fileName=post.getPostPhoto();

        String uploadDir="./postPhoto/"+post.getId();

        FileUploadUtil.deleteFile(uploadDir, fileName);

        return postService.deletePost(id);
    }
}
