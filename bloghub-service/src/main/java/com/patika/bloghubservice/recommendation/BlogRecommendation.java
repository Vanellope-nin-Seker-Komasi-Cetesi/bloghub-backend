package com.patika.bloghubservice.recommendation;

import com.patika.bloghubservice.dto.response.BlogResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class BlogRecommendation {

    private List<BlogResponse> blogRecResponses=new ArrayList<>();

}
