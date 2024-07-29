package com.patika.bloghubservice.repository;


import com.patika.bloghubservice.model.Blog;
import com.patika.bloghubservice.model.User;
import com.patika.bloghubservice.model.enums.BlogStatus;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BlogRepository {

    private Map<String, Blog> blogMap = new HashMap<>();

    public Blog save(Blog blog) {
        return blogMap.put(blog.getTitle(), blog);
    }

    public Optional<Blog> findByTitle(String title) {
        return blogMap.values()
                .stream()
                .filter(blog -> blog.getTitle().equals(title))
                .filter(blog -> !blog.getBlogStatus().equals(BlogStatus.DELETED))
                .findFirst();
    }

    public List<Blog> findAll() {
        return blogMap.values().stream().toList();
    }

    public void addComment(String title, Blog blog) {
        blogMap.remove(title);
        blogMap.put(title, blog);
    }

    public void likeBlog(String title, Blog blog) {
        blogMap.remove(title);
        blogMap.put(title, blog);
    }

    public List<Blog> getPublishedAndDraftBlogs(User user) {

        return blogMap.values().stream()
                .filter(blog -> blog.getUser().equals(user))
                .filter(blog -> !blog.getBlogStatus().equals(BlogStatus.DELETED))
                .collect(Collectors.toList());
    }

    public void deleteDraftBlog(String email,String title) {

        Optional<Blog> optionalBlog = blogMap.values().stream()
                .filter(blog -> blog.getUser().getEmail().equals(email))
                .filter(blog -> blog.getTitle().equals(title))
                .filter(blog -> blog.getBlogStatus().equals(BlogStatus.DRAFT))
                .findFirst();

        optionalBlog.ifPresent(blog -> blogMap.remove(blog.getTitle()));


    }


    public List<Blog> findByUserBlogs(User user) {
        return blogMap.values().stream()
                .filter(blog -> blog.getUser().equals(user))
                .collect(Collectors.toList());
    }
}
