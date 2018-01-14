package com.example.blog.domain;

import java.util.List;

public interface BlogService {
    List<BlogPost> getAllPosts();
    BlogPost createBlogPost(BlogPost blogPost);
    List<BlogPost> getAll(User user);
}
