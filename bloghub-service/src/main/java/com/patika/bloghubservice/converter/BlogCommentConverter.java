package com.patika.bloghubservice.converter;

import com.patika.bloghubservice.dto.request.BlogCommentRequest;
import com.patika.bloghubservice.dto.response.BlogCommentResponse;
import com.patika.bloghubservice.model.BlogComment;
import com.patika.bloghubservice.model.User;
import com.patika.bloghubservice.model.enums.BlogCommentType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogCommentConverter {

    public static List<BlogCommentResponse> toResponse(List<BlogComment> blogCommentList) {
        return blogCommentList.stream()
                .map(BlogCommentConverter::toResponse)
                .toList();
    }

    public static BlogCommentResponse toResponse(BlogComment blogComment) {
        return new BlogCommentResponse(blogComment.getComment());
    }

    public static BlogComment toEntity(User user, BlogCommentRequest request) {
        BlogComment blogComment = new BlogComment();
        blogComment.setUser(user);
        blogComment.setComment(request.comment());
        blogComment.setCreatedDate(LocalDateTime.now());
        blogComment.setBlogCommentType(BlogCommentType.INITIAL);
        return blogComment;
    }
}
