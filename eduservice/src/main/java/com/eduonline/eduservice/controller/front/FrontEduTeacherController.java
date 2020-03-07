package com.eduonline.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eduonline.common.R;
import com.eduonline.eduservice.entity.EduCourse;
import com.eduonline.eduservice.entity.EduTeacher;
import com.eduonline.eduservice.service.EduCourseService;
import com.eduonline.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 前台讲师controller
 */
@RestController
@RequestMapping("/eduservice/fronteduteacher")
@CrossOrigin
public class FrontEduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    /**
     * 分页获取所有讲师
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("getFrontAllTeacherList/{page}/{limit}")
    public R getFrontAllTeacherList(@PathVariable("page") Long page,
                               @PathVariable("limit") Long limit){
        Page<EduTeacher> teacherPage = new Page<>(page, limit);
        Map<String,Object> map = eduTeacherService.getFrontAllTeacherList(teacherPage);

        return R.ok().data(map);
    }

    /**
     * 根据id查询讲师信息 以及课程列表
     * @param teacherId
     * @return
     */
    @GetMapping("getFrontTeacherInfoByid/{teacherId}")
    public R getFrontTeacherInfoByid(@PathVariable("teacherId") String teacherId){
        //查询讲师信息
        EduTeacher teacherInfo = eduTeacherService.getById(teacherId);
        //查询所属讲师的课程信息
        List<EduCourse> coursesInfo = eduCourseService.getCourseInfoByTeacherId(teacherId);
        return R.ok().data("teacherInfo",teacherInfo).data("coursesInfo",coursesInfo);
    }
}
