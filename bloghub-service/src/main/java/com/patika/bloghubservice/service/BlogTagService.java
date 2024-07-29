package com.patika.bloghubservice.service;

import com.patika.bloghubservice.model.BlogTag;
import com.patika.bloghubservice.repository.BlogTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogTagService {

    private final BlogTagRepository blogTagRepository;

    public BlogTagService(BlogTagRepository blogTagRepository) {
        this.blogTagRepository = blogTagRepository;
    }

    public BlogTag createTag(String tag)
    {

        return blogTagRepository.createTag(tag);

    }

    public List<BlogTag> getAllTags() {

        return blogTagRepository.getAllTags();
    }
}
