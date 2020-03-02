package com.eduonline.eduservice.mapper;

import com.eduonline.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eduonline.eduservice.entity.dto.form.EduAllCourseInfoDto;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    //根据课程id查询课程详细信息
    EduAllCourseInfoDto getAllCourseInfoById(@RequestParam("id") String id);
}
