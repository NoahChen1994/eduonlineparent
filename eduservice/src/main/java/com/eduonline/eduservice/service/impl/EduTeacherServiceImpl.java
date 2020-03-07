package com.eduonline.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eduonline.eduservice.entity.EduCourse;
import com.eduonline.eduservice.entity.EduTeacher;
import com.eduonline.eduservice.entity.query.QueryTeacher;
import com.eduonline.eduservice.mapper.EduTeacherMapper;
import com.eduonline.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    /**
     * 后台分页获取所有讲师
     * @param teacherPage
     * @param query
     * @return
     */
    @Override
    public IPage<EduTeacher> getCondtionPageList(Page<EduTeacher> teacherPage, QueryTeacher query) {
        //条件为空
        if(query==null){
            return  baseMapper.selectPage(teacherPage,null);
        }

        //不为空拼接条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = query.getName();
        String level = query.getLevel();
        String begin = query.getBegin();
        String end = query.getEnd();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        return  baseMapper.selectPage(teacherPage,wrapper);
    }

    /**
     * 前台分页获取所有讲师
     * @param teacherPage
     * @return
     */
    @Override
    public Map<String, Object> getFrontAllTeacherList(Page<EduTeacher> teacherPage) {
        //分页查询得到分页对象
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        baseMapper.selectPage(teacherPage,queryWrapper);

        //获取数据填充集合
        List<EduTeacher> records = teacherPage.getRecords();//每页数据
        long total = teacherPage.getTotal(); //总记录数
        long size = teacherPage.getSize(); //每页显示的记录数
        long pages = teacherPage.getPages(); //总页数
        long current = teacherPage.getCurrent(); //当前页码
        boolean hasNext = teacherPage.hasNext(); //是否有下一页
        boolean hasPrevious = teacherPage.hasPrevious(); //是否有上一页
        Map<String, Object> map = new HashMap<>();
        map.put("items",records);
        map.put("total",total);
        map.put("size",size);
        map.put("pages",pages);
        map.put("current",current);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);
        return map;
    }

}
