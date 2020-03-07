package com.eduonline.eduucenter.controller;


import com.eduonline.common.R;
import com.eduonline.eduucenter.service.UcenterMemberService;
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

}

