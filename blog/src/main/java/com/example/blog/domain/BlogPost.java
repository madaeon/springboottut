package com.example.blog.domain;

public class BlogPost {
    private final String id;
    private final User author;
    private final String title;
    private final String text;

    public BlogPost(String id, User author, String title, String text) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.text = text;
    }

    public User getAuthor() {
        return author;
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
