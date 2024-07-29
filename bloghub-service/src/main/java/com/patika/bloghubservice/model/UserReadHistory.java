package com.patika.bloghubservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReadHistory {

    private String title;
    private String text;
    private String author;
    private String image;
    private LocalDateTime readTime;


}
