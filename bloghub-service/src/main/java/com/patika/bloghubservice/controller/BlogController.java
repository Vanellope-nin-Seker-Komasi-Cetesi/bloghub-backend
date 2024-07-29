package com.patika.bloghubservice.controller;

import com.patika.bloghubservice.converter.BlogConverter;
import com.patika.bloghubservice.dto.request.BlogCommentRequest;
import com.patika.bloghubservice.dto.request.BlogSaveRequest;
import com.patika.bloghubservice.dto.request.BlogTagRequest;
import com.patika.bloghubservice.dto.response.BlogCommentResponse;
import com.patika.bloghubservice.dto.response.BlogResponse;
import com.patika.bloghubservice.dto.response.GenericResponse;
import com.patika.bloghubservice.messages.GenericMessage;
import com.patika.bloghubservice.model.Blog;
import com.patika.bloghubservice.model.enums.BlogStatus;
import com.patika.bloghubservice.service.BlogService;
import com.patika.bloghubservice.service.BlogTagService;
import com.patika.bloghubservice.service.UserReadHistoryService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final BlogTagService blogTagService;
    private final UserReadHistoryService readHistoryService;

    @PostMapping("/users/{email}")
    public GenericResponse<BlogResponse> createBlog(@RequestBody BlogSaveRequest request, @PathVariable String email) {
        return GenericResponse.success(blogService.createBlog(email, request), HttpStatus.CREATED);
    }

    @GetMapping
    public GenericResponse<List<BlogResponse>> getAllBlogs() {
        return GenericResponse.success(blogService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{email}/published-and-draft")
    public GenericResponse<List<BlogResponse>> getPublishedAndDraftBlogs(@PathVariable String email)
    {

        return GenericResponse.success(blogService.getPublishedAndDraftBlogs(email),HttpStatus.OK);
    }

    @DeleteMapping("/{title}/users/{email}/delete")
    public GenericMessage deleteBlog(@PathVariable String email,@PathVariable String title)
    {
        blogService.deleteDraftBlog(email,title);
        return new GenericMessage("Blog silindi");
    }
    @PatchMapping("/{title}/users/{email}")
    public GenericMessage changeBlogStatus(@PathVariable String title, @PathVariable String email, @PathParam("blogStatus") BlogStatus blogStatus)
    {
        blogService.changeBlogStatus(blogStatus,title,email);
        return new GenericMessage("Blog Status başarıyla değiştirildi");
    }


    @GetMapping("/{title}")
    public GenericResponse<BlogResponse> getBlogByTitle(@PathVariable String title) {
        Blog foundBlog=blogService.getBlogByTitle(title);

        readHistoryService.addReadHistory(foundBlog);

        BlogResponse response=BlogConverter.toResponse(foundBlog);
        return GenericResponse.success(response,HttpStatus.OK);
    }

    @PutMapping("/{title}/users/{email}")
    public GenericMessage addComment(@PathVariable String title, @PathVariable String email, @RequestBody BlogCommentRequest comment) {
        blogService.addComment(title, email, comment);
        return new GenericMessage("Yorumunuz iletildi");
    }

    @PutMapping("/{title}/add-tag")
    public GenericMessage addBlogTag(@PathVariable String title, @RequestBody BlogTagRequest tag) {
        blogService.addBlogTag(title,tag);
        return new GenericMessage("Tag eklendi");
    }

    @PutMapping("/{title}/users/{email}/like-count")
    public void likeBlog(@PathVariable String title, @PathVariable String email) {
        //bir kullanıcı sadece maksimum 50 kere beğenebilir
        blogService.likeBlog(title, email);
    }

    @GetMapping("/{title}/like-count")
    public Long getLikeCountByTitle(@PathVariable String title) {
        return blogService.getLikeCountByTitle(title);
    }

    @GetMapping("/{title}/comments")
    public List<BlogCommentResponse> getBlogComments(@PathVariable String title)
    {

        return blogService.getBlogComments(title);

    }



//commentleri getiren end-point

    //kullanıcı sadece kendi blog'larını gören endpoint

    // resim yükleme
}
