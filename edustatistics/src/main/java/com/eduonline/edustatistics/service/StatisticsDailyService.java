package com.eduonline.edustatistics.service;

import com.eduonline.edustatistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-03-04
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    //生成统计数据
    void createStatisticsByDate(String day);

    //根据条件查询统计信息
    Map<String, Object> getCountData(String type, String begin, String end);
}
