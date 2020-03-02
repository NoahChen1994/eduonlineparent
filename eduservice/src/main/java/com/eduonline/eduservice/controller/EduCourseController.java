package com.eduonline.eduservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eduonline.common.R;
import com.eduonline.eduservice.entity.EduCourse;
import com.eduonline.eduservice.entity.dto.form.EduAllCourseInfoDto;
import com.eduonline.eduservice.entity.dto.form.EduCourseInfoDto;
import com.eduonline.eduservice.entity.query.QueryCourse;
import com.eduonline.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    EduCourseService eduCourseService;

    /**
     * 添加课程信息
     * @param eduCourseInfoDto
     * @return
     */
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody EduCourseInfoDto eduCourseInfoDto){
        String courseId = eduCourseService.insertCourseInfo(eduCourseInfoDto);
        return R.ok().data("courseId",courseId);
    }

    /**
     * 通过Id查询课程信息
     * @param id
     * @return
     */
    @GetMapping("getCourseInfoById/{id}")
    public R getCourseInfoById(@PathVariable String id){
        EduCourseInfoDto eduCourseInfoDto =  eduCourseService.getCourseInfoById(id);
        return R.ok().data("courseInfoForm", eduCourseInfoDto);
    }

    /**
     * 更新课程信息
     * @param eduCourseInfoDto
     * @return
     */
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody EduCourseInfoDto eduCourseInfoDto){
        String courseId = eduCourseService.updateCourseInfo(eduCourseInfoDto);
            return R.ok().data("courseId",courseId);
    }

    /**
     * 条件分页查询课程信息
     * @param page
     * @param limit
     * @param query
     * @return
     */
    @PostMapping("getCoursePageList/{page}/{limit}")
    public R getCoursePageList(@PathVariable long page,
                               @PathVariable long limit,
                               @RequestBody(required = false) QueryCourse query){
        Page<EduCourse> coursePage = new Page<>(page, limit);
        IPage<EduCourse> list = eduCourseService.getCondtionPageList(coursePage,query);
        long total = list.getTotal(); //总条数
        List<EduCourse> records = list.getRecords(); //当前页的具体数据
        return R.ok().data("total",total).data("items",records);
    }

    /**
     * 根据id删除课程信息
     * @param id
     * @return
     */
    @DeleteMapping("deleteCourseById/{id}")
    public R deleteCourseById(@PathVariable String id){

        boolean result = eduCourseService.deleteCourseById(id);
        if (result){
            return R.ok();
        }else {
            return R.error();
        }

    }

    /**
     * 根据课程id查询课程详细信息
     * @param id
     * @return
     */
    @GetMapping("getAllCourseInfoById/{id}")
    public R getAllCourseInfoById(@PathVariable String id){
        EduAllCourseInfoDto courseInfoDto =  eduCourseService.getAllCourseInfoById(id);
        return R.ok().data("items",courseInfoDto);
    }

    /**
     * 根据课程id修改课程发布状态
     * @param id
     * @return
     */
    @GetMapping("updateCourseStatus/{id}")
    public R updateCourseStatus(@PathVariable String id){
        boolean flag = eduCourseService.updateCourseStatus(id);
        if (flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }
}

