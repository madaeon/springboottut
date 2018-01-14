package com.example.blog.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogPostRepository extends CrudRepository<BlogPostEntity, Integer> {
    List<BlogPostEntity> findByAuthor(UserEntity author);
}
