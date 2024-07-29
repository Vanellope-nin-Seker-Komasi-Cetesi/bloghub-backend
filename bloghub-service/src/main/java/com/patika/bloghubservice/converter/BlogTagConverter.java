package com.patika.bloghubservice.converter;

import com.patika.bloghubservice.dto.request.BlogTagRequest;
import com.patika.bloghubservice.model.BlogTag;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogTagConverter {

    public static BlogTag toEntity(BlogTagRequest request) {
        BlogTag blogTag = new BlogTag();
        blogTag.setTag(request.tag());
        return blogTag;
    }
}
