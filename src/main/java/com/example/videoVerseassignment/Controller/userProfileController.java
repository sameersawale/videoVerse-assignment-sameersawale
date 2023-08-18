package com.example.videoVerseassignment.Controller;

import com.example.videoVerseassignment.DTO.UserProfileDto;
import com.example.videoVerseassignment.Entity.User;
import com.example.videoVerseassignment.Entity.UserProfile;
import com.example.videoVerseassignment.Service.Impl.UserProfileServiceImpl;
import com.example.videoVerseassignment.fileUpload.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/userProfile")
public class userProfileController {

    @Autowired
    UserProfileServiceImpl userProfileService;

        @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String signUp(@RequestParam("file") MultipartFile multipartFile, @RequestPart("user")UserProfileDto userProfileDto) throws Exception {
        String fileName=multipartFile.getOriginalFilename();

        userProfileDto.setProfilePhoto(fileName);

        UserProfile userProfile=userProfileService.addUserProfile(userProfileDto);

        String uploadDir="./profilePhoto/"+userProfile.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "user profile added successfully.....";
    }
}
