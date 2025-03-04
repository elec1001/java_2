package com.example.spring.basicboardv1.config.security;

import com.example.spring.basicboardv1.dto.SignInRequestDTO;
import com.example.spring.basicboardv1.dto.SignInResponseDTO;
import com.example.spring.basicboardv1.model.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class CustomAutnenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();//인증정보 담음
        Member member = userDetails.getMember();

        //세션 설정
        HttpSession session = request.getSession();
        session.setAttribute("userId", member.getUserId());
        session.setAttribute("userName", member.getUserName());

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=UTF-8");

        SignInResponseDTO build = SignInResponseDTO.builder()
                .success(true)
                .userId(member.getUserId())
                .userName(member.getUserName())
                .build();

        response.getWriter()
                .write(objectMapper.writeValueAsString(build));//객체를 스트링 형태로 네트워크상으로 보냄(직렬화)
    }
}
