package com.eduonline.edustatistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eduonline.common.R;
import com.eduonline.edustatistics.client.UcenterClient;
import com.eduonline.edustatistics.entity.StatisticsDaily;
import com.eduonline.edustatistics.mapper.StatisticsDailyMapper;
import com.eduonline.edustatistics.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-03-04
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    UcenterClient ucenterClient;

    /**
     * 生成统计数据
     * @param day
     */
    @Override
    public void createStatisticsByDate(String day) {
        //先根据统计日期初始化统计数据
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date_calculated",day);
        Integer result = baseMapper.selectCount(queryWrapper);
        if (result>0){
            baseMapper.delete(queryWrapper);
        }

        //调用服务获取每日注册人数
        R registNumByDay = ucenterClient.getRegistNumByDay(day);
        Integer registerNum  = (Integer) registNumByDay.getData().get("items");

        //数据模拟
        //每日登录人数
        Integer loginNum = RandomUtils.nextInt(100, 200);//TODO
        //每日视频播放次数
        Integer videoViewNum = RandomUtils.nextInt(100, 200);//TODO
        //每日新增课程数
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO

        //添加数据
        StatisticsDaily daily = new StatisticsDaily();
        daily.setDateCalculated(day);
        daily.setLoginNum(loginNum);
        daily.setRegisterNum(registerNum);
        daily.setCourseNum(courseNum);
        daily.setVideoViewNum(videoViewNum);

        baseMapper.insert(daily);
    }

    /**
     * 根据条件查询统计信息
     * @param type
     * @param begin
     * @param end
     * @return
     */
    @Override
    public Map<String, Object> getCountData(String type, String begin, String end) {
        //要返回的数据
        Map<String, Object> map = new HashMap<>();

        //拼接条件
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated",begin,end);
        //只需要查询日期和统计因子
        queryWrapper.select(type,"date_calculated");

        //封装时间
        List<String> timeList = new ArrayList<>();

        //封装数据
        List<Integer> dataList = new ArrayList<>();

        List<StatisticsDaily> Dailies = baseMapper.selectList(queryWrapper);
        for (int i = 0; i < Dailies.size(); i++) {
            //取得每一条记录
            StatisticsDaily daily = Dailies.get(i);
            //封装数据
            timeList.add(daily.getDateCalculated());

            //不同的统计因子取值不同
            switch (type){
                case"register_num":
                  dataList.add(daily.getRegisterNum());
                  break;
                case"login_num":
                    dataList.add(daily.getLoginNum());
                    break;
                case"video_view_num":
                    dataList.add(daily.getVideoViewNum());
                    break;
                case"course_num":
                    dataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        map.put("dataList",dataList);
        map.put("timeList",timeList);
        return map;
    }
}
