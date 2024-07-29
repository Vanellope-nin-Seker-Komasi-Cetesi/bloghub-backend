package com.patika.bloghubservice.service;

import com.patika.bloghubservice.converter.BlogCommentConverter;
import com.patika.bloghubservice.converter.BlogConverter;
import com.patika.bloghubservice.converter.BlogTagConverter;
import com.patika.bloghubservice.converter.UserConverter;
import com.patika.bloghubservice.dto.request.BlogCommentRequest;
import com.patika.bloghubservice.dto.request.BlogSaveRequest;
import com.patika.bloghubservice.dto.request.BlogTagRequest;
import com.patika.bloghubservice.dto.response.BlogCommentResponse;
import com.patika.bloghubservice.dto.response.BlogRecommendationResponse;
import com.patika.bloghubservice.dto.response.BlogResponse;
import com.patika.bloghubservice.exception.*;
import com.patika.bloghubservice.file.FileService;
import com.patika.bloghubservice.model.Blog;
import com.patika.bloghubservice.model.BlogComment;
import com.patika.bloghubservice.model.User;
import com.patika.bloghubservice.model.enums.BlogStatus;
import com.patika.bloghubservice.model.enums.StatusType;
import com.patika.bloghubservice.repository.BlogRepository;
import com.patika.bloghubservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final FileService fileService;

    public BlogResponse createBlog(String email, BlogSaveRequest request) {

        //ödev: kullanıcı blog yayınlamak için approved bir statuye sahip olmalı

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

        if(!user.getStatusType().equals(StatusType.APPROVED))
        {
            throw new UserNotApprovedException();
        }

        String image=fileService.saveBase64StringFile(request.getImage());

        Blog blog = new Blog(request.getTitle(), request.getText(), user, image);

        blogRepository.save(blog);

        return BlogConverter.toResponse(blog);
    }

    public Blog getBlogByTitle(String title) {
        return blogRepository.findByTitle(title).orElseThrow(() -> new BlogNotFoundException(title));
    }

    public void addComment(String title, String email, BlogCommentRequest comment) {

        Blog foundBlog = getBlogByTitle(title);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

        if(!user.getStatusType().equals(StatusType.APPROVED))
        {
            throw new UserNotApprovedException();
        }

        BlogComment blogComment = BlogCommentConverter.toEntity(user, comment);

        foundBlog.getBlogCommentList().add(blogComment);

        blogRepository.addComment(title, foundBlog);

    }

    public List<Blog> getBlogsFilterByStatus(BlogStatus blogStatus, String email) {

        Optional<User> foundUser = userRepository.findByEmail(email);

        return foundUser.get().getBlogList().stream()
                .filter(blog -> blogStatus.equals(blog.getBlogStatus()))
                .toList();
    }

    public void changeBlogStatus(BlogStatus blogStatus, String title, String email) {

        Blog foundBlog = getBlogByTitle(title);

        if(!foundBlog.getUser().getEmail().equals(email))
        {
           throw new UnauthorizedModificationException();

        }

        if (foundBlog.getBlogStatus().equals(BlogStatus.PUBLISHED)) {
            throw new BlogStatusException();
        }


        foundBlog.setBlogStatus(blogStatus);

    }

    public void deleteDraftBlog(String email, String title)
    {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        Blog foundBlog=getBlogByTitle(title);

        if(!foundBlog.getUser().getEmail().equals(user.getEmail()))
        {
            throw new UnauthorizedModificationException();
        }

        if (foundBlog.getBlogStatus().equals(BlogStatus.PUBLISHED)) {
            throw new BlogStatusException();
        }

        blogRepository.deleteDraftBlog(user.getEmail(),foundBlog.getTitle());

    }



    public List<BlogResponse> getAll() {
        return BlogConverter.toResponse(blogRepository.findAll());
    }

    public void likeBlog(String title, String email) {

        User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException(email));
        Blog blog = getBlogByTitle(title);

        if(blog.getLikeCount()>=50)
        {
            throw new BlogLikeLimitException();
        }
        if(!blog.getUser().getEmail().equals(user.getEmail()))
        {
            blog.setLikeCount(blog.getLikeCount() + 1);
        }

        blogRepository.likeBlog(title, blog);

    }

    public Long getLikeCountByTitle(String title) {

        Blog blog = getBlogByTitle(title);

        return blog.getLikeCount();
    }

    public List<BlogResponse> getPublishedAndDraftBlogs(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        List<Blog> publishedAndDraftBlogs=blogRepository.getPublishedAndDraftBlogs(user);

        return BlogConverter.toResponse(publishedAndDraftBlogs);
    }

    public List<BlogCommentResponse> getBlogComments(String title) {
        Blog foundBlog=getBlogByTitle(title);
        return BlogCommentConverter.toResponse(foundBlog.getBlogCommentList());
    }

    public void addBlogTag(String title, BlogTagRequest blogTagRequest) {

        Blog blog = getBlogByTitle(title);

        blog.getBlogTags().add(BlogTagConverter.toEntity(blogTagRequest));
        blogRepository.save(blog);

    }

    public List<BlogRecommendationResponse> getBlogRecommendations(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        List<Blog> blogs = blogRepository.findByUserBlogs(user);

        List<BlogRecommendationResponse> responses = UserConverter.toBlogRecommendationResponseList(user, blogs);
        return responses;
    }
}
