package com.eduonline.eduservice.controller;


import com.eduonline.common.R;
import com.eduonline.eduservice.entity.EduChapter;
import com.eduonline.eduservice.entity.dto.chapter.EduChapterDto;
import com.eduonline.eduservice.service.EduChapterService;
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
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    EduChapterService eduChapterService;

    /**
     * 根据课程id查询章节信息
     * @param id
     * @return
     */
    @GetMapping("getChapterInfoById/{id}")
    public R getChapterInfoById(@PathVariable String id){
        List<EduChapterDto> chapterNestedList =  eduChapterService.getChapterInfoById(id);
        return R.ok().data("items",chapterNestedList);
    }

    /**
     * 添加章节
     * @param chapter
     * @return
     */
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter chapter){
       boolean flag = eduChapterService.addChapter(chapter);
       if(flag){
           return R.ok();
       }else {
           return R.error();
       }
    }

    /**
     * 修改章节
     * @param chapter
     * @return
     */
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter chapter){
        boolean flag = eduChapterService.updateChapter(chapter);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }


    /**
     * 删除章节
     * @param id
     * @return
     */
    @DeleteMapping("deleteChapter/{id}")
    public R deleteChapter(@PathVariable String id){
        boolean flag =  eduChapterService.deleteChapterByChapterId(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 根据课程id查询课程信息
     * @return
     */
    @GetMapping("getChapterByChapterId/{id}")
    public R getChapterByChapterId(@PathVariable String id){
        EduChapter chapter = eduChapterService.getChapterByChapterId(id);
        return R.ok().data("items",chapter);
    }
}

