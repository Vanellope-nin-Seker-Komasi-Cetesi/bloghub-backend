package com.patika.bloghubservice.converter;

import com.patika.bloghubservice.dto.response.BlogResponse;
import com.patika.bloghubservice.model.Blog;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogConverter {

    public static BlogResponse toResponse(Blog blog) {
        return BlogResponse.builder()
                .title(blog.getTitle())
                .text(blog.getText())
                .blogStatus(blog.getBlogStatus())
                .likeCount(blog.getLikeCount())
                .createdDateTime(blog.getCreatedDate())
                .image(blog.getImage())
                .blogTags(blog.getBlogTags())
                .blogCommentList(BlogCommentConverter.toResponse(blog.getBlogCommentList()))
                .build();
    }

    public static List<BlogResponse> toResponse(List<Blog> blogs) {
        return blogs.stream().map(BlogConverter::toResponse).toList();
    }
}
