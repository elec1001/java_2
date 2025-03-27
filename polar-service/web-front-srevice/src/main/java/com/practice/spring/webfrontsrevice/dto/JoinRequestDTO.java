package com.practice.spring.webfrontsrevice.dto;

import com.practice.spring.webfrontsrevice.type.Role;
import lombok.Getter;

@Getter
public class JoinRequestDTO {
    private String userId;
    private String password;
    private String userName;
    private Role role;
}
