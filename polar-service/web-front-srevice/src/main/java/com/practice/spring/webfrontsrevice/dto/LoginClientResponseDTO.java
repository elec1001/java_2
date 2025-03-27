package com.practice.spring.webfrontsrevice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginClientResponseDTO {
    private boolean loggedIn;
    private String userName;
    private String userId;
    private String accessToken;
    private String refreshToken;

    public LoginResponseDTO toLoginResponseDTO() {
        return LoginResponseDTO.builder()
                .userName(userName)
                .userId(userId)
                .accessToken(accessToken)
                .url(loggedIn ? "/webs/home" : "/webs/login")
                .message(loggedIn ? "로그인 성공!" : "로그인 실패!")
                .build();
    }
}
