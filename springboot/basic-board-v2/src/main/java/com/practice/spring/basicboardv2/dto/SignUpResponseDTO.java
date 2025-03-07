package com.practice.spring.basicboardv2.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpResponseDTO {
    public Boolean successed;
    public String userId;
    public String userName;
    public String role;
}
