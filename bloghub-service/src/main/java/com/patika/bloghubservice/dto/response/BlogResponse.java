package com.patika.bloghubservice.dto.response;

import com.patika.bloghubservice.model.BlogTag;
import com.patika.bloghubservice.model.enums.BlogStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogResponse {

    private String title;
    private String text;
    private LocalDateTime createdDateTime;
    private BlogStatus blogStatus;
    private Long likeCount;
    private String image;
    private Set<BlogTag> blogTags=new HashSet<>();
    private List<BlogCommentResponse> blogCommentList = new ArrayList<>();

}
