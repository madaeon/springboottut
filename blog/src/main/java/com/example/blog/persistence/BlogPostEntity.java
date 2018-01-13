package com.example.blog.persistence;

import javax.persistence.*;

@Entity(name = "blog_posts")
public class BlogPostEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name="title")
    private String title;

    @Lob
    @Column(name = "text")
    private String text;

    public BlogPostEntity() {
    }

    public BlogPostEntity(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
