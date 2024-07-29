package com.patika.bloghubservice.dto.response;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogRecommendationResponse {
    private String user;
    private String blogTitle;
    private String blogText;
    private LocalDateTime createdBlogDate;
    private Long likeCount;

}

