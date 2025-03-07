package com.practice.spring.basicboardv2.service;

import com.practice.spring.basicboardv2.config.security.CustomUserDetails;
import com.practice.spring.basicboardv2.mapper.MemberMapper;
import com.practice.spring.basicboardv2.model.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Member member=memberMapper.findByUserId(username);
        if(member==null){
            throw new UsernameNotFoundException(username+"not found");
        }
    return CustomUserDetails.builder()
            .member(member)
            .roles(List.of(String.valueOf(member.getRole().name())))
            .build();
    }

}
