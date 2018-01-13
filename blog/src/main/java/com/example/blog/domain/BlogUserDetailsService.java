package com.example.blog.domain;

import com.example.blog.persistence.UserEntity;
import com.example.blog.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BlogUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public BlogUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOne(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Username " + username + " not found.");
        }
        return new User(userEntity.getUsername(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getPasswordHash(),
                userEntity.getUserRole());
    }
}
