package com.patika.bloghubservice.controller;

import com.patika.bloghubservice.dto.request.BlogTagRequest;
import com.patika.bloghubservice.dto.response.GenericResponse;
import com.patika.bloghubservice.model.BlogTag;
import com.patika.bloghubservice.service.BlogTagService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
public class BlogTagController {

    private final BlogTagService blogTagService;

    public BlogTagController(BlogTagService blogTagService) {
        this.blogTagService = blogTagService;
    }


    @PostMapping()
    public GenericResponse<BlogTag> createTag(@RequestBody BlogTagRequest request) {
        return GenericResponse.success(blogTagService.createTag(request.tag()), HttpStatus.CREATED);
    }

    @GetMapping("")
    public GenericResponse<List<BlogTag>> getAllTags()
    {
        return GenericResponse.success(blogTagService.getAllTags(),HttpStatus.OK);
    }
}
