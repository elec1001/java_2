package com.practice.spring.basicboardv2.config.jwt;

import com.practice.spring.basicboardv2.model.Member;
import com.practice.spring.basicboardv2.type.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public String generateToken(Member member, Duration expiredAt) {
        Date now = new Date();
        return makeToken(
                new Date( now.getTime()+expiredAt.toMillis()),
                member
        );
    }

    public Member getTokenDetails(String token) {
        Claims claims=getClaims(token);

        return  Member.builder()
                .id(claims.get("id",Long.class))
                .userId(claims.getSubject())
                .userName(claims.get("username",String.class))
                .role(
                        Role.valueOf(claims.get("role",String.class))
                )
                .build();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims=getClaims(token);

        //claims에서 역할을 추출하고,GrantedAuthoirty로 변환
        List<GrantedAuthority> authorities =Collections.singletonList(
                new SimpleGrantedAuthority(claims.get("role",String.class))
        );

        //UserDetails 생성객체
        UserDetails userDetails=new User(claims.getSubject(),"",authorities);

        //UsernamePasswordAuthenticationToken
        return new UsernamePasswordAuthenticationToken(userDetails,token,authorities);
    }

    public int validateToken(String token) {
        try {
            getClaims(token);
            return 1;
        } catch (ExpiredJwtException e) {
            log.info("token expired");//토근이 만료된 경우
            return 2;
        } catch (Exception e) {
            //복호화 과정에서 에러가 나면 유효하지 않은 토큰
            System.out.println("token error");
            return 3;
            // 더이상 진행이 안됨
        }

    }




    public String makeToken(Date expired, Member member) {

        Date now= new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expired)
                .setSubject(member.getUserId())//페이로드의 서브
                .claim("id", member.getId())
                .claim("role", member.getRole())//클레임은 넣고 싶은 데이터란 의미임
                .claim("userName",member.getUserName()) //민감한 정보는 안됨.(패스워드 연락처 주소 등)
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)//서명
                .compact();


    }

    private Claims getClaims(String token) {

            return Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

    }

    private SecretKey getSecretKey() {
            byte[] keyBytes = Base64.getDecoder().decode(jwtProperties.getSecretKey());
            return Keys.hmacShaKeyFor(keyBytes);
    }


   }
