package com.practice.spring.basicboardv2.controller;

import com.practice.spring.basicboardv2.config.jwt.TokenProvider;
import com.practice.spring.basicboardv2.config.security.CustomUserDetails;
import com.practice.spring.basicboardv2.dto.SignInRequestDTO;
import com.practice.spring.basicboardv2.dto.SignInResponseDTO;
import com.practice.spring.basicboardv2.dto.SignUpResponseDTO;
import com.practice.spring.basicboardv2.dto.SingUpRequestDTO;
import com.practice.spring.basicboardv2.model.Member;
import com.practice.spring.basicboardv2.service.MemberService;
import com.practice.spring.basicboardv2.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @PostMapping("/join")
    public SignUpResponseDTO signUp(@RequestBody SingUpRequestDTO signupRequestSTO){
        System.out.println(signupRequestSTO);

        // 회원 정보를 Member 객체로 변환 후 저장
        memberService.signUp(signupRequestSTO.toMember(bCryptPasswordEncoder));

        // 회원가입 성공 응답 반환
        return SignUpResponseDTO.builder()
                .successed(true)
                .build();
    }

    @PostMapping("/login")
    public SignInResponseDTO signIn(@RequestBody SignInRequestDTO signinRequestSTO,
                                    HttpServletResponse response){
        System.out.println(signinRequestSTO);
        //사용자 인증
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signinRequestSTO.getUsername(),
                        signinRequestSTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        Member member = ((CustomUserDetails)authenticate.getPrincipal()).getMember();

        String accessToken = tokenProvider.generateToken(member, Duration.ofHours(2));
        String refreshToken = tokenProvider.generateToken(member, Duration.ofDays(2));

        CookieUtil.addCookie(response,"refreshToken",refreshToken,7*24*60*60);

        return SignInResponseDTO.builder()
                .isLoggedIn(true)
                .token(accessToken)
                .userId(member.getUserId())
                .userName(member.getUserName())
                .build();
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request,
                       HttpServletResponse response){
        CookieUtil.deleteCookie(request,response,"refreshToken");
    }




}
