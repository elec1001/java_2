package com.practice.spring.basicboardv2.model;

import com.practice.spring.basicboardv2.type.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Member {
    private Long id;
    private String userId;
    private String password;
    private String userName;
    private Role role;
}
