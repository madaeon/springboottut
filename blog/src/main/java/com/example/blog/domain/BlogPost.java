package com.example.blog.domain;

public class BlogPost {
    private final String id;
    private final String title;
    private final String text;

    public BlogPost(String id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
