package com.eduonline.test;

import com.eduonline.eduucenter.entity.UcenterMember;
import com.eduonline.eduucenter.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.Test;

public class TestJwt {
    @Test //令牌生成
    public void testGenJsonWebToken(){
        UcenterMember member = new UcenterMember();
        member.setId("1106833021442510849");
        member.setNickname("saber");
        member.setAvatar("http://hahaha/hahah.png");
        String token = JwtUtils.genJsonWebToken(member);
        System.out.println(token);
    }

    @Test //获取信息
    public void  testCheckJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJndWxpIiw" +
                        "iaWQiOiI5OTkiLCJuaWNrbmFtZSI6ImphdmF0ZXN0IiwiYXZhdGFyIjoiaHR0cDovL2" +
                        "hhaGFoYS9oYWhhaC5wbmciLCJpYXQiOjE1ODM2NTMxMDksImV4cCI6MTU4MzY1NDkwOX0.mGr2D-" +
                        "08goIRlJ95i0Ld1jDpfK9eDSy10I9e-StYPlY";
        Claims claims = JwtUtils.checkJwt(token);
        String id = (String)claims.get("id");
        String nickname = (String)claims.get("nickname");
        String avatar = (String)claims.get("avatar");
        System.out.println(id);
        System.out.println(nickname);
        System.out.println(avatar);
    }
}
