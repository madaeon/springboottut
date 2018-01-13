package com.example.blog.domain;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BlogServiceStub implements BlogService {

    @Override
    public List<BlogPost> getAllPosts() {
        List<BlogPost> blogPosts = new LinkedList<>();

        blogPosts.add(new BlogPost("1", "erster BlogPost", "Mein erster Blogpost."));
        blogPosts.add(new BlogPost("2", "zweiter BlogPost", "Mein zweiter Blogpost."));

        return blogPosts;
    }
}
