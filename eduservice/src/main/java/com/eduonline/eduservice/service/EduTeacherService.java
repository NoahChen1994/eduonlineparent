package com.eduonline.eduservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eduonline.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eduonline.eduservice.entity.query.QueryTeacher;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
public interface EduTeacherService extends IService<EduTeacher> {

    IPage<EduTeacher> getCondtionPageList(Page<EduTeacher> teacherPage, QueryTeacher query);
}
