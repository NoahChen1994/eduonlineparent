package com.eduonline.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eduonline.eduservice.entity.EduTeacher;
import com.eduonline.eduservice.entity.query.QueryTeacher;
import com.eduonline.eduservice.mapper.EduTeacherMapper;
import com.eduonline.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
}
