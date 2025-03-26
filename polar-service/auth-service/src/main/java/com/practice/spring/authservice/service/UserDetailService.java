package com.practice.spring.authservice.service;

import com.practice.spring.authservice.config.security.CustomUserDetails;
import com.practice.spring.authservice.mapper.UserMapper;
import com.practice.spring.authservice.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserDetailService implements UserDetailsService {

    private final UserMapper userMapper;

    public UserDetailService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userByUserId = userMapper.findUserByUserId(username);

        if (userByUserId == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return CustomUserDetails.builder()
                .user(userByUserId)
                .roles(List.of(String.valueOf(userByUserId.getRole())))
                .build();
    }
}
