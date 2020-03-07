package com.eduonline.edustatistics.controller;


import com.eduonline.common.R;
import com.eduonline.edustatistics.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-03-04
 */
@CrossOrigin
@RestController
@RequestMapping("/edustatistics")
public class StatisticsDailyController {

    @Autowired
    StatisticsDailyService dailyService;

    /**
     * 生成统计数据
     * @param day
     * @return
     */
    @PostMapping("createStatisticsByDate/{day}")
    public R createStatisticsByDate(@PathVariable("day") String day){
        dailyService.createStatisticsByDate(day);
        return R.ok();
    }

    /**
     * 返回图表显示的数据
     *          第一部分时间：["2020-03-05","2020-03-06"]
     *          第二部分数量：[5,3]
     *      type:   统计因子 比如查询注册人数
     *      begin:  开始时间
     *      end:    结束时间
     * @return
     */
    @GetMapping("getCountData/{type}/{begin}/{end}")
    public R getCountData(@PathVariable String type,
                          @PathVariable String begin,
                          @PathVariable String end){
        Map<String,Object> map = dailyService.getCountData(type,begin,end);

        return R.ok().data("items",map);
    }
}

