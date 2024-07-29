package com.patika.bloghubservice.repository;

import com.patika.bloghubservice.model.BlogTag;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class BlogTagRepository {

    private Set<BlogTag> blogTags = new HashSet<>();

    public BlogTag createTag(String tag) {
        BlogTag newTag = new BlogTag(tag);
        blogTags.add(newTag);
        return newTag;
    }

    public List<BlogTag> getAllTags() {

        return blogTags.stream().toList();
    }
}
