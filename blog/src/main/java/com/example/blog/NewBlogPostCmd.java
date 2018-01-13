package com.example.blog;

public class NewBlogPostCmd {

    private String title;
    private String text;

    public NewBlogPostCmd() {

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
}
