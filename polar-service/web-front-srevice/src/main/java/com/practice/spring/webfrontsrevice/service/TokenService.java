package com.practice.spring.webfrontsrevice.service;

import com.practice.spring.webfrontsrevice.client.AuthClient;
import com.practice.spring.webfrontsrevice.dto.RefreshTokenClientResponseDTO;
import com.practice.spring.webfrontsrevice.dto.RefreshTokenRequestDTO;
import com.practice.spring.webfrontsrevice.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AuthClient authClient;

    public RefreshTokenClientResponseDTO refreshToken(Cookie[] cookies) {

        String refreshToken = CookieUtil.getCookieValue(cookies, "refresh_token");

        if (refreshToken == null) {
            return null;
        }

        return authClient.refresh(
                RefreshTokenRequestDTO.builder()
                        .refreshToken(refreshToken)
                        .build());
    }
}
