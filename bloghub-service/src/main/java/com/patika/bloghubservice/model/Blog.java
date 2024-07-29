package com.patika.bloghubservice.model;

import com.patika.bloghubservice.model.enums.BlogStatus;
import com.patika.bloghubservice.validation.LikeLimit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Blog {

    private String title;
    private String text;
    private LocalDateTime createdDate;
    private User user;
    private BlogStatus blogStatus;
    @LikeLimit
    private Long likeCount;
    private String image;
    private List<BlogComment> blogCommentList = new ArrayList<>();

    private Set<BlogTag> blogTags=new HashSet<>();


    public Blog(String title, String text, User user,String image) {
        this.title = title;
        this.text = text;
        this.user = user;
        this.createdDate = LocalDateTime.now();
        this.blogStatus = BlogStatus.DRAFT;
        this.likeCount = 0L;
        this.image=image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BlogStatus getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(BlogStatus blogStatus) {
        this.blogStatus = blogStatus;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    //1.yol olarak bu metodu yazabiliriz ama custom validation constraint  ve custom exception ile mesaj verecem

   /* public void setLikeCount(Long likeCount) {
        if (likeCount <= 50) {
            this.likeCount = likeCount;
        } else {
            throw new IllegalArgumentException("Like count cannot exceed 50");
        }
    }*/

    public List<BlogComment> getBlogCommentList() {
        return blogCommentList;
    }

    public void setBlogCommentList(List<BlogComment> blogCommentList) {
        this.blogCommentList = blogCommentList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<BlogTag> getBlogTags() {
        return blogTags;
    }

    public void setBlogTags(Set<BlogTag> blogTags) {
        this.blogTags = blogTags;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", createdDate=" + createdDate +
                ", user=" + user +
                ", blogStatus=" + blogStatus +
                '}';
    }
}
