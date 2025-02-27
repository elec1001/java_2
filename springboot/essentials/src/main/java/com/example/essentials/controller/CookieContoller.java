package com.example.essentials.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CookieContoller {
    @GetMapping("/set-cookie")
    public String setCookie(){
        return "set-cookie";
    }
    @GetMapping("/get-cookie")
    public String getCookieExc(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        String username = null;

        if(cookies != null) {
            model.addAttribute("username", username);
            model.addAttribute("message", "쿠키에서 사용자 정보를 읽었습니다");
        }else {
            model.addAttribute("massege","쿠키가 존해자지 않습니다");
        }
        return "result-cookie";
    }

    @PostMapping("/set-cookie")
    public String setCookieExc(@RequestParam String username,
                               HttpServletResponse response, Model model){
        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(7*24*60*60); //1주일
        cookie.setPath("/");
        cookie.setHttpOnly(true);//자바스크립로 접근불가

        response.addCookie(cookie);

        model.addAttribute("message","쿠키가 설정되었습니다.");
        model.addAttribute("username",username);
        return "result-cookie";
    }

    @GetMapping("delete-cookie")
    public String deleteCookieExc(HttpServletResponse response, Model model){
        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0); // 쿠키 삭제 효과
        cookie.setPath("/");

        response.addCookie(cookie);

        model.addAttribute("message","쿠키가 삭제되었습니다");

        return "result-cookie";
    }
}
