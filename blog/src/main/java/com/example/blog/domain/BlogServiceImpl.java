package com.example.blog.domain;

import com.example.blog.persistence.BlogPostEntity;
import com.example.blog.persistence.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogPostRepository blogPostRepository;

    @Autowired
    public BlogServiceImpl(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public List<BlogPost> getAllPosts() {
        List<BlogPost> result = new LinkedList<>();
        for (BlogPostEntity blogPostEntity: blogPostRepository.findAll()) {
            result.add(new BlogPost(
                    blogPostEntity.getId().toString(),
                    blogPostEntity.getTitle(),
                    blogPostEntity.getText()
            ));
        }
        return result;
    }

    @Override
    public BlogPost createBlogPost(BlogPost blogPost) {
        BlogPostEntity blogPostEntity = new BlogPostEntity(blogPost.getTitle(), blogPost.getText());
        BlogPostEntity savedEntity = blogPostRepository.save(blogPostEntity);

        BlogPost saved = new BlogPost(savedEntity.getId().toString(),
                savedEntity.getTitle(), savedEntity.getText());

        return saved;
    }


}
