package com.eduonline.eduservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eduonline.eduservice.entity.EduCourse;
import com.eduonline.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eduonline.eduservice.entity.query.QueryTeacher;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
public interface EduTeacherService extends IService<EduTeacher> {

    //后台分页获取所有讲师
    IPage<EduTeacher> getCondtionPageList(Page<EduTeacher> teacherPage, QueryTeacher query);

    //前台分页获取所有讲师
    Map<String, Object> getFrontAllTeacherList(Page<EduTeacher> teacherPage);

}
