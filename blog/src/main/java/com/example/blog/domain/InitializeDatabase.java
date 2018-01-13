package com.example.blog.domain;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class InitializeDatabase implements InitializingBean {

    private final BlogUserDetailsService userDetailsService;
    private final BlogService blogService;

    @Autowired
    public InitializeDatabase(BlogUserDetailsService userDetailsService, BlogService blogService) {
        this.userDetailsService = userDetailsService;
        this.blogService = blogService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            userDetailsService.loadUserByUsername("bob");
        } catch (UsernameNotFoundException enfe) {
            userDetailsService.addUser(new User("bob", "Bob", "Page", "password", "User"));
        }
        try {
            userDetailsService.loadUserByUsername("peter");
        } catch (UsernameNotFoundException enfe) {
            userDetailsService.addUser(new User("peter", "Peter", "Parker", "password", "User"));
        }
        if (blogService.getAllPosts().isEmpty()) {
            blogService.createBlogPost(new BlogPost(null, userDetailsService.loadUserByUsername("bob"),
                    "mein erster eintrag", "lorem ipsum dolor sit amet..."));
            blogService.createBlogPost(new BlogPost(null, userDetailsService.loadUserByUsername("peter"),
                    "mein zweiter eintrag", "Wants pawn term, dare worsted ladle gull hoe lift wetter" +
                    " murder inner ladle cordage, honor itch offer lodge dock florist."));
        }
    }
}