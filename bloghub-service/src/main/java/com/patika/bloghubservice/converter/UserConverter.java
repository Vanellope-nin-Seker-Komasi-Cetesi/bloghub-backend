package com.patika.bloghubservice.converter;

import com.patika.bloghubservice.dto.response.BlogRecommendationResponse;
import com.patika.bloghubservice.dto.response.UserResponse;
import com.patika.bloghubservice.model.Blog;
import com.patika.bloghubservice.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {

    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .bio(user.getBio())
                .userType(user.getUserType())
                .build();
    }

    public static List<UserResponse> toResponse(List<User> users) {
        return users.stream().map(UserConverter::toResponse).toList();
    }

    public static List<BlogRecommendationResponse> toBlogRecommendationResponseList(User user, List<Blog> blogs) {
        return blogs.stream().map(blog -> BlogRecommendationResponse.builder()
                        .user(user.getEmail())
                        .blogTitle(blog.getTitle())
                        .blogText(blog.getText())
                        .createdBlogDate(blog.getCreatedDate())
                        .likeCount(blog.getLikeCount())
                        .build())
                .collect(Collectors.toList());
    }
}
