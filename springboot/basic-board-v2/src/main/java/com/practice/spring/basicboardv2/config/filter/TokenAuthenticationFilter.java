package com.practice.spring.basicboardv2.config.filter;

import com.practice.spring.basicboardv2.config.jwt.TokenProvider;
import com.practice.spring.basicboardv2.model.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    
    public final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX="Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        logger.info("requestURI: {}"+requestURI);
        if("/refresh-token".equals(requestURI)){
            filterChain.doFilter(request,response);
            return;
        }
        String token = resolveToken(request);
     //   System.out.println("token:"+token);
     //  response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        if (token != null && tokenProvider.validateToken(token)==1) {
            //토큰이 유효할 경우 인증정보를 설정

            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Member member = tokenProvider.getTokenDetails(token);
            request.setAttribute("member", member);
        }else if (token!=null && tokenProvider.validateToken(token)==2){
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           return; //더이상 진행 시키지 않음
        }

        filterChain.doFilter(request,response);

    }

    private String resolveToken(HttpServletRequest request){

       String bearerToken = request.getHeader(HEADER_AUTHORIZATION);

       if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
           return bearerToken.substring(TOKEN_PREFIX.length());
       }
       return null;
        
    }
}
