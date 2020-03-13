package com.eduonline.eduucenter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/zuultest")
public class ZuulCookieTestController {

    @RequestMapping("cookie")
    public void zuulCookieTest(HttpServletRequest request){
        String cookie = request.getHeader("cookie");
        String token = request.getHeader("token");
        System.out.println("cookie:"+cookie);
        System.out.println("token:"+token);
    }
}
