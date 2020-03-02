package com.eduonline.eduservice.service;

import com.eduonline.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eduonline.eduservice.entity.dto.chapter.EduChapterDto;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
public interface EduChapterService extends IService<EduChapter> {

    //根据课程id删除章节
    void deleteChapterById(String courseId);

    //根据课程id查询章节信息
    List<EduChapterDto> getChapterInfoById(String courseId);

    //添加章节
    boolean addChapter(EduChapter chapter);

    //修改章节
    boolean updateChapter(EduChapter chapter);

    //根据id删除章节
    boolean deleteChapterByChapterId(String id);

    //根据id查询章节信息
    EduChapter getChapterByChapterId(String id);
}
