package com.patika.bloghubservice.controller;

import com.patika.bloghubservice.dto.request.BlogTagRequest;
import com.patika.bloghubservice.dto.request.PasswordUpdate;
import com.patika.bloghubservice.dto.request.UserSaveRequest;
import com.patika.bloghubservice.dto.response.BlogResponse;
import com.patika.bloghubservice.dto.response.GenericResponse;
import com.patika.bloghubservice.dto.response.UserResponse;
import com.patika.bloghubservice.messages.GenericMessage;
import com.patika.bloghubservice.model.enums.StatusType;
import com.patika.bloghubservice.service.BlogService;
import com.patika.bloghubservice.service.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BlogService blogService;

    @PostMapping()
    public GenericResponse<UserResponse> createUser(@Valid @RequestBody UserSaveRequest request) {
        return GenericResponse.success(userService.saveUser(request), HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public GenericResponse<UserResponse> getUserByEmail(@PathVariable String email) {
        return GenericResponse.success(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping
    public GenericResponse<List<UserResponse>> getAllUsers() {
        return GenericResponse.success(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{email}")
    public GenericMessage changeStatus(@PathVariable String email, @PathParam("statusType") StatusType statusType) {
        userService.changeStatus(email, statusType);
        return new GenericMessage("Kullanıcı status değiştirildi");
    }

    @PutMapping()
    public void changeStatus() {
        // userService.changeStatusBulk(); --ödev
    }

    //ödev şifre değiştirenn endpoint

    @PatchMapping("/{email}/password")
    public GenericMessage updatePassword(@PathVariable String email, @Valid @RequestBody PasswordUpdate passwordUpdate){
        userService.updatePassword(email, passwordUpdate);
        return new GenericMessage("Password updated successfully");
    }

    @PutMapping("/{email}/add-tag")
    public GenericMessage addBlogTag(@PathVariable String email, @RequestBody BlogTagRequest tag) {
        userService.addBlogTag(email,tag);
        return new GenericMessage("Tag eklendi");
    }

    @GetMapping("/{email}/blogs")
    public GenericResponse<List<BlogResponse>> getUserBlogs(@PathVariable String email) {
        return GenericResponse.success(blogService.getUserBlogs(email), HttpStatus.OK);
    }

    @GetMapping("/{email}/other-blogs")
    public GenericResponse<List<BlogResponse>> getUserNotBlogs(@PathVariable String email) {
        return GenericResponse.success(blogService.getUserNotBlogs(email), HttpStatus.OK);
    }

}
