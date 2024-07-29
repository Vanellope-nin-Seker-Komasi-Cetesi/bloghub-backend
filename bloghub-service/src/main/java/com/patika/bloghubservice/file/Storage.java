package com.patika.bloghubservice.file;

public class Storage {

    private static String root="uploads";
    private static String images="blog";

    public static String getRoot() {
        return root;
    }

    public static String getImages() {
        return images;
    }
}
