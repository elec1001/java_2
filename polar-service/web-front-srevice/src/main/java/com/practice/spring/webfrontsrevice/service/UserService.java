package com.practice.spring.webfrontsrevice.service;

import com.practice.spring.webfrontsrevice.client.AuthClient;
import com.practice.spring.webfrontsrevice.dto.JoinClientResponseDTO;
import com.practice.spring.webfrontsrevice.dto.JoinRequestDTO;
import com.practice.spring.webfrontsrevice.dto.LoginClientResponseDTO;
import com.practice.spring.webfrontsrevice.dto.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthClient authClient;

    public JoinClientResponseDTO join(JoinRequestDTO joinRequestDTO) {
        return authClient.join(joinRequestDTO);
    }

    public LoginClientResponseDTO login(LoginRequestDTO loginRequestDTO) {
        log.info("login={}",loginRequestDTO);
        return authClient.login(loginRequestDTO);
    }
}
