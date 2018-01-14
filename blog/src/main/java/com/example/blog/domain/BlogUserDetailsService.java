package com.example.blog.domain;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface BlogUserDetailsService extends UserDetailsService {
    User loadUserByUsername(String s) throws UsernameNotFoundException;
    void addUser(User user);
    List<User> getAll();
    User getOne(String username);
}
