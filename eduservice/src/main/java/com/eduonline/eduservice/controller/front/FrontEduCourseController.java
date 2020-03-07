package com.eduonline.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eduonline.common.R;
import com.eduonline.eduservice.entity.EduCourse;
import com.eduonline.eduservice.entity.dto.chapter.EduChapterDto;
import com.eduonline.eduservice.service.EduChapterService;
import com.eduonline.eduservice.service.EduCourseService;
import com.eduonline.eduservice.vo.FrontCourseInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

/**
 * 前台课程controller
 */
@RestController
@RequestMapping("/eduservice/fronteduteacher")
@CrossOrigin
public class FrontEduCourseController {

    @Autowired
    EduCourseService eduCourseService;

    @Autowired
    EduChapterService eduChapterService;

    /**
     * 分页获取所有课程
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("getFrontAllCourseList/{page}/{limit}")
    public R getFrontAllTeacherList(@PathVariable("page") Long page,
                                    @PathVariable("limit") Long limit){
        Page<EduCourse> eduCoursePage = new Page<>(page,limit);
        Map<String,Object> map = eduCourseService.getFrontAllTeacherList(eduCoursePage);
        return R.ok().data(map);
    }

    /**
     * 查询课程详细信息
     * @param courseId
     * @return
     */
    @GetMapping("getAllCourseInfoByCourseId/{courseId}")
    public R getAllCourseInfoByCourseId(@PathVariable String courseId){
        //根据课程id查询课程基本信息 课程描述信息 讲师信息 分类信息
        FrontCourseInfoVo courseInfo = eduCourseService.getAllCourseInfoByCourseId(courseId);

        //根据课程id查询课程章节信息 课程小节信息
        List<EduChapterDto> chapterInfo = eduChapterService.getChapterInfoById(courseId);

        return R.ok().data("courseInfo",courseInfo).data("chapterInfo",chapterInfo);
    }
}
