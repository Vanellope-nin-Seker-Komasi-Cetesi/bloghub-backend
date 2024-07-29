package com.patika.bloghubservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlogTag {

    private String tag;

    private List<Blog> blogList=new ArrayList<>();

    public BlogTag(String tag) {
        this.tag = tag;
    }

    public BlogTag(String tag, List<Blog> blogList) {
        this.tag = tag;
        this.blogList = blogList;
    }

    public BlogTag() {
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogTag blogTag = (BlogTag) o;
        return Objects.equals(tag, blogTag.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }
}
