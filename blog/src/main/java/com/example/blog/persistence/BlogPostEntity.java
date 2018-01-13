package com.example.blog.persistence;

import javax.persistence.*;

@Entity(name = "blog_posts")
public class BlogPostEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    private UserEntity author;

    @Column(name="title")
    private String title;

    @Lob
    @Column(name = "text")
    private String text;

    public BlogPostEntity() {
    }

    public BlogPostEntity(UserEntity author, String title, String text) {
        this.title = title;
        this.author = author;
        this.text = text;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
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
