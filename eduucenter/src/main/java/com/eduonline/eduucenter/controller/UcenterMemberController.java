package com.eduonline.eduucenter.controller;


import com.eduonline.common.R;
import com.eduonline.eduucenter.entity.UcenterMember;
import com.eduonline.eduucenter.service.UcenterMemberService;
import com.eduonline.eduucenter.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-03-04
 */
@RestController
@RequestMapping("/eduucenter")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    UcenterMemberService memberService;

    /**
     * 统计某一天的注册人数
     * @param day
     * @return
     */
    @GetMapping("getRegistNumByDay/{day}")
    public R getRegistNumByDay(@PathVariable String day){
        Integer result =  memberService.getRegistNumByDay(day);
        return R.ok().data("items",result);
    }

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    @GetMapping("getUserInfoToken/{token}")
    public R getUserInfoToken(@PathVariable String token){
        Claims claims = JwtUtils.checkJwt(token);
        String id = (String)claims.get("id");
        String nickname = (String)claims.get("nickname");
        String avatar = (String)claims.get("avatar");
        UcenterMember member = new UcenterMember();
        member.setId(id);
        member.setNickname(nickname);
        member.setAvatar(avatar);
        return R.ok().data("items",member);
    }

}

