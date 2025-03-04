package com.example.spring.basicboardv1.config;

import com.example.spring.basicboardv1.config.security.CustomAuthenticationFailureHandler;
import com.example.spring.basicboardv1.config.security.CustomAutnenticationSuccessHandler;
import com.example.spring.basicboardv1.mapper.MemberMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web)->web.ignoring()
                .requestMatchers(
                        "/static/**","/css/**","/js/**"
                );
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           CustomAutnenticationSuccessHandler successHandler,
                                           CustomAuthenticationFailureHandler failureHandler)
                                            throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests
                                .requestMatchers(

                                        new AntPathRequestMatcher("/join","POST"),
                                        new AntPathRequestMatcher("/member/join","GET"),
                                        new AntPathRequestMatcher("/member/login","GET")

                                ).permitAll()
                                 .anyRequest().authenticated()

                );

        http
                .formLogin(
                        formLogin-> formLogin
                                .loginPage("/member/login")
                                .loginProcessingUrl("/login")
                                .successHandler(successHandler)
                                .failureHandler(failureHandler)
                );

        return http.build();

    }

    @Bean//스프링 컨테이너에 등록
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();//메서드 생성.
    }
}
