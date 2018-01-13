package com.example.blog.domain;

import com.example.blog.persistence.BlogPostEntity;
import com.example.blog.persistence.BlogPostRepository;
import com.example.blog.persistence.UserEntity;
import com.example.blog.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogPostRepository blogPostRepository;
    private final UserRepository userRepository;
    private final BlogUserDetailsService blogUserDetailsService;

    @Autowired
    public BlogServiceImpl(BlogPostRepository blogPostRepository, UserRepository userRepository, BlogUserDetailsService blogUserDetailsService) {
        this.blogPostRepository = blogPostRepository;
        this.userRepository = userRepository;
        this.blogUserDetailsService = blogUserDetailsService;
    }

    @Override
    public List<BlogPost> getAllPosts() {
        List<BlogPost> result = new LinkedList<>();
        for (BlogPostEntity blogPostEntity: blogPostRepository.findAll()) {
            result.add(new BlogPost(
                    blogPostEntity.getId().toString(),
                    blogUserDetailsService.loadUserByUsername(blogPostEntity.getAuthor().getUsername()),
                    blogPostEntity.getTitle(),
                    blogPostEntity.getText()
            ));
        }
        return result;
    }

    @Override
    public BlogPost createBlogPost(BlogPost blogPost) {
        UserEntity userEntity = userRepository.findOne(blogPost.getAuthor().getUsername());

        BlogPostEntity blogPostEntity = new BlogPostEntity(userEntity, blogPost.getTitle(), blogPost.getText());
        BlogPostEntity savedEntity = blogPostRepository.save(blogPostEntity);

        BlogPost saved = new BlogPost(savedEntity.getId().toString(),
                blogUserDetailsService.loadUserByUsername(savedEntity.getAuthor().getUsername()),
                savedEntity.getTitle(), savedEntity.getText());

        return saved;
    }


}
