package com.patika.bloghubservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BlogSaveRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String text;
    private String image;
}
