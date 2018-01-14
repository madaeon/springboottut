package com.example.blog.domain;

import com.example.blog.persistence.UserEntity;
import com.example.blog.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BlogUserDetailsServiceImpl implements BlogUserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public BlogUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
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

    @Override
    public void addUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setUsername(user.getUsername());
        userEntity.setUserRole("USER");
        userEntity.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntity);
    }
    @Override
    public List<User> getAll() {
        List<User> users = new LinkedList<>();

        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(new User(userEntity.getUsername(),
                    userEntity.getFirstName(),
                    userEntity.getLastName(),
                    userEntity.getPasswordHash(),
                    userEntity.getUserRole())
            );
        }

        return users;
    }

    @Override
    public User getOne(String username) {
        return (User) loadUserByUsername(username);
    }
}
