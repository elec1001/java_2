package com.practice.spring.webfrontsrevice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshTokenRequestDTO {
    private String refreshToken;
}
