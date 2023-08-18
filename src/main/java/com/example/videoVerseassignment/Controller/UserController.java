package com.example.videoVerseassignment.Controller;


import com.example.videoVerseassignment.DTO.LoginDto;
import com.example.videoVerseassignment.DTO.LoginResponse;
import com.example.videoVerseassignment.DTO.SignUpDto;
import com.example.videoVerseassignment.DTO.UserUpdateDto;
import com.example.videoVerseassignment.Entity.User;
import com.example.videoVerseassignment.Security.JwtTokenProvider;
import com.example.videoVerseassignment.Service.Impl.UserServiceImpl;
import com.example.videoVerseassignment.fileUpload.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserDetailsService userDetailsService;


    @PostMapping(value = "/signup", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addUser(@RequestParam("file") MultipartFile multipartFile, @RequestPart("user")SignUpDto signUpDto) throws Exception {
        String fileName=multipartFile.getOriginalFilename();

        signUpDto.setProfilePhoto(fileName);

        User user=userService.signUp(signUpDto);

        String uploadDir="./profilePhoto/"+user.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        String result=user.getUsername()+" added successfully......";

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @GetMapping("/get/{id}")
    public User getUser(@PathVariable("id") int id) throws Exception {
        return userService.getUserById(id);
    }
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(
            @RequestBody LoginDto loginDto) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUserName(),
                        loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication);
        return ResponseEntity.ok(new LoginResponse(jwt));
    }



    @PutMapping(value = "/update/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String updateUser(@PathVariable("id") int id, @RequestPart("user") UserUpdateDto userUpdateDto, @RequestParam("file") MultipartFile multipartFile) throws Exception {
        User user=userService.getUserById(id);
        String oldFileName=user.getProfilePhoto();
        String newFileName=multipartFile.getOriginalFilename();
        user.setProfilePhoto(newFileName);

        userService.updateUser(id, userUpdateDto);

        String uploadDir="./profilePhoto/"+user.getId();


        FileUploadUtil.updateFile(uploadDir, oldFileName, newFileName, multipartFile);

        return "user update successfully.....";

    }


}
