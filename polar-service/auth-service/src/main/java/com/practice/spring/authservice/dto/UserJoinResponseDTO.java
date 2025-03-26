package com.practice.spring.authservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserJoinResponseDTO {
    private boolean isSuccess;
}
