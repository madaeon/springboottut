package com.example.blog.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

public class User extends org.springframework.security.core.userdetails.User {

    private final String firstName;
    private final String lastName;

    public User(String username, String firstName, String lastName, String password, String role) {
        super(username, password, Arrays.asList(new SimpleGrantedAuthority(role)));
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "User{"
                + "super='" + super.toString() + "'"
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + '}';
    }
}
