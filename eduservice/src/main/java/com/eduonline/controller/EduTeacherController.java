package com.eduonline.controller;


import com.eduonline.entity.EduTeacher;
import com.eduonline.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
@RestController
@RequestMapping("/edu-teacher")
public class EduTeacherController {

    @Autowired
    EduTeacherService teacherService;

    @GetMapping
    public List<EduTeacher> getAllTeachers(){
        return teacherService.list(null);
    }

}

