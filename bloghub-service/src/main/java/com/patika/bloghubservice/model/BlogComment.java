package com.patika.bloghubservice.model;

import com.patika.bloghubservice.model.enums.BlogCommentType;

import java.time.LocalDateTime;

public class BlogComment {

    private User user;
    private String comment;
    private LocalDateTime createdDate;
    private BlogCommentType blogCommentType;

    public BlogComment() {
    }

    public BlogComment(User user, String comment) {
        this.user = user;
        this.comment = comment;
        this.createdDate = LocalDateTime.now();
        this.blogCommentType = BlogCommentType.INITIAL;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public BlogCommentType getBlogCommentType() {
        return blogCommentType;
    }

    public void setBlogCommentType(BlogCommentType blogCommentType) {
        this.blogCommentType = blogCommentType;
    }

    @Override
    public String toString() {
        return "BlogComment{" +
                "user=" + user +
                ", comment='" + comment + '\'' +
                ", createdDate=" + createdDate +
                ", blogCommentType=" + blogCommentType +
                '}';
    }
}
