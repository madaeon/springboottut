package com.example.blog.persistence;

import org.springframework.data.repository.CrudRepository;

public interface BlogPostRepository extends CrudRepository<BlogPostEntity, Integer> {
}
